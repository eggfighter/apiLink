package com.kt.psso.onm.scheduler.hc.realname;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class KaitClient {
	private static Log LOG = LogFactory.getLog(KaitClient.class);

	/**
	 * @uml.property  name="host"
	 */
	private String host;
	/**
	 * @uml.property  name="port"
	 */
	private int port;
	/**
	 * @uml.property  name="key"
	 */
	private String key = "";
	/**
	 * @uml.property  name="message"
	 */
	private String message = "";

	/**
	 * @uml.property  name="sock"
	 */
	private Socket sock;
	/**
	 * @uml.property  name="in"
	 */
	private DataInputStream in;
	/**
	 * @uml.property  name="out"
	 */
	private DataOutputStream out;

	/**
	 * @uml.property  name="head" multiplicity="(0 -1)" dimension="1"
	 */
	private byte[] head = new byte[2];
	/**
	 * @uml.property  name="body" multiplicity="(0 -1)" dimension="1"
	 */
	private byte[] body = null;
	
	public KaitClient(String host, int port, String key, String message) {
		this.host = host;
		this.port = port;
		this.key = key;
		this.message = message;
	}

	public boolean run() {

		try {
			if (LOG.isInfoEnabled()) {
				LOG.info("Socket 초기화");
			}

			init();

			if (LOG.isInfoEnabled()) {
				LOG.info("데이터생성");
			}

			makeHeadBody(this.message);

			if (LOG.isInfoEnabled()) {
				LOG.info("전송");
			}

			if (!sendMessage()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("SEND DATA ERROR");
				}
				return false;
			}

			if (LOG.isInfoEnabled()) {
				LOG.info("수신");
			}

			if (!receive()) {
				if (LOG.isWarnEnabled()) {
					LOG.warn("RECEIVE DATA ERROR");
				}
				return false;
			}

			return true;
		} catch (UnknownHostException e) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(e.getMessage(), e);
			}
			return false;
		} catch (IOException e) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(e.getMessage(), e);
			}
			return false;
		} finally {
			if (this.out != null) try { this.out.close(); } catch(Exception e) {}
			if (this.in != null) try { this.in.close(); } catch(Exception e) {}
			if (this.sock != null) try { this.sock.close(); } catch(Exception e) {}
		}
		
	}

	private void init() throws UnknownHostException, IOException {
		this.sock = new Socket(this.host, this.port);
		this.in = new DataInputStream(new BufferedInputStream(this.sock.getInputStream()));
		this.out = new DataOutputStream(new BufferedOutputStream(this.sock.getOutputStream()));
	}

	public boolean sendMessage() {
		if (!sendHead()) {
			return false;
		}

		if (!sendBody()) {
			return false;
		}
		
		return true;
	}

	public void makeHeadBody(String str) throws UnsupportedEncodingException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("message = " + str);
		}

		this.head = str.substring(0, 2).getBytes("8859_1");
		byte[] lpszIDCD = str.substring(2, 6).getBytes("8859_1");
		String strIDCD = new String(lpszIDCD, "KSC5601");
		byte[] data = (str.substring(6, str.length()).getBytes());

		if (LOG.isDebugEnabled()) {
			LOG.debug("Head = " + str.substring(0, 2));
			LOG.debug("IDCD = " + strIDCD);
			LOG.debug("Data = " + str.substring(6, str.length()));
			LOG.debug("Key  = " + this.key);

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < data.length; i++) {
				sb.append(Byte.toString(data[i]) + " ");
			}
			LOG.debug("bytes = " + sb.toString());
		}

		byte[] lpszBuffer = Base64.base64Encode(SymmetricCipher.SEED_CBC_ENCRYPT(data, this.key.getBytes()));

		this.body = new byte[4 + lpszBuffer.length];

		for (int i = 0; i < 4; i++)
			this.body[i] = lpszIDCD[i];
		for (int i = 0; i < lpszBuffer.length; i++)
			this.body[i + 4] = lpszBuffer[i];

		if (LOG.isDebugEnabled()) {
			LOG.debug("Packet Data = ["
					+ Integer.toString(this.body.length) + "]["
					+ new String(this.body) + "]");
		}
	}

	// 헤더 전송
	synchronized public boolean sendHead() {
		try {
			this.out.write(this.head);
			// 헤더와 바디부분이 따로 전송되지않도록 아래부분 주석 또는 삭제처리
			// this.out.flush();
			return true;
		} catch (Exception e) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(e.getMessage(), e);
			}
			return false;
		}
	}

	// 바디 전송
	synchronized public boolean sendBody() {
		try {
			this.out.write(this.body);
			this.out.flush();
			return true;
		} catch (Exception e) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(e.getMessage(), e);
			}
			return false;
		}
	}

	// 서버로부터 데이터를 수신
	synchronized public boolean receive() throws IOException {
		byte[] data = getSocketMessage();

		if (data == null) return false;

		// byte[] decryptText = SymmetricCipher.SEED_CBC_DECRYPT(data, this.key.getBytes());

		if (LOG.isDebugEnabled()) {
			LOG.debug("data = " + new String(data));
		}

		return true;
	}

	public byte[] getSocketMessage() throws IOException {

		byte[] tempBuffer = new byte[1024];
		byte[] buffer = null;
		byte[] base64Buffer = null;

		int count = 0;

		if (this.in.read(tempBuffer) != -1) {
			for (int i = 0; i < tempBuffer.length; i++) {
				if (tempBuffer[i] == 0x00) break;
				count++;
			}
		}

		buffer = new byte[count];

		for (int j = 0; j < count; j++) {
			buffer[j] = tempBuffer[j];
		}

		// base64Buffer = Base64.base64Decode(buffer);

		// return base64Buffer;

		return buffer;

	}

}
