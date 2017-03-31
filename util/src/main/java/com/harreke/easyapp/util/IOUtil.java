package com.harreke.easyapp.util;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 由 Harreke（harreke@live.cn） 创建于 2015/03/20
 */
public class IOUtil {
    public static byte[] getBytes(int i) {
        byte[] bytes = new byte[4];

        bytes[3] = (byte) (i & 0xff);
        bytes[2] = (byte) (i >> 8 & 0xff);
        bytes[1] = (byte) (i >> 16 & 0xff);
        bytes[0] = (byte) (i >> 24 & 0xff);

        return bytes;
    }

    public static byte[] getBytes(short s) {
        byte[] bytes = new byte[2];

        bytes[1] = (byte) (s & 0xff);
        bytes[0] = (byte) (s >> 8 & 0xff);

        return bytes;
    }

    public static int getInt(byte[] bytes, int offset) {
        int b0 = bytes[offset] & 0xff;
        int b1 = bytes[offset + 1] & 0xff;
        int b2 = bytes[offset + 2] & 0xff;
        int b3 = bytes[offset + 3] & 0xff;

        return (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
    }

    public static boolean isSocketAvailable(Socket socket) {
        return socket != null && !socket.isClosed();
    }

    public static byte[] read(@NonNull Socket socket, int count) throws IOException {
        byte[] result = null;
        int length = 0;
        int read;
        InputStream stream;

        if (count > 0) {
            result = new byte[count];
            while (length < count) {
                stream = socket.getInputStream();
                read = stream.read(result, length, count - length);
                if (read == -1) {
                    throw new IOException();
                }
                length += read;
            }
        }

        return result;
    }

    public static byte[] read(@NonNull InputStream stream, int count) throws IOException {
        byte[] result = null;
        int length = 0;
        int read;

        if (count > 0) {
            result = new byte[count];
            while (length < count) {
                read = stream.read(result, length, count - length);
                if (read == -1) {
                    throw new IOException();
                }
                length += read;
            }
        }

        return result;
    }

    public static int readInt(InputStream stream) throws IOException {
        byte[] bytes;

        if (stream != null) {
            bytes = read(stream, 4);

            return getInt(bytes, 0);
        } else {
            throw new IOException();
        }
    }

    public static boolean skip(@NonNull Socket socket, int count) throws IOException {
        int length = 0;
        long skip;
        InputStream stream;

        if (count > 0) {
            while (length < count) {
                stream = socket.getInputStream();
                skip = stream.skip(count - length);
                if (skip == -1) {
                    throw new IOException();
                }
                length += skip;
            }
        }

        return length == count;
    }

    public static boolean skip(InputStream stream, int count) throws IOException {
        long length = 0l;
        long skip;

        if (stream != null) {
            while (length < count) {
                skip = stream.skip(count - length);
                if (skip == 0) {
                    break;
                }
                length += skip;
            }
        }

        return length == count;
    }

    public static void write(@NonNull Socket socket, byte[] bytes) throws IOException {
        OutputStream stream = socket.getOutputStream();

        stream.write(bytes);
        stream.flush();
    }
}