package com.BibleQuote.bqtj.android.utils;

import android.util.Log;
import com.BibleQuote.bqtj.utils.ILogSys;

/**
 * Created with IntelliJ IDEA.
 * User: Nikita K.
 * Date: 17.11.13
 * Time: 0:12
 * To change this template use File | Settings | File Templates.
 */
public final class LogSysAndroid implements ILogSys {

	private static LogSysAndroid logSysAndroid;


	private LogSysAndroid() {
	}

	public static synchronized LogSysAndroid getLogSysAndroid() {

		if (logSysAndroid == null) {
			logSysAndroid = new LogSysAndroid();
		}

		return logSysAndroid;
	}


	public void d(String tag, String msg) {
		Log.d(tag, msg);
	}

	public void d(String tag, String msg, Throwable tr) {
		Log.d(tag, msg, tr);
	}

	public void e(String tag, String msg) {
		Log.e(tag, msg);
	}

	public void e(String tag, String msg, Throwable tr) {
		Log.e(tag, msg, tr);
	}

	public void i(String tag, String msg) {
		Log.i(tag, msg);
	}

	public void i(String tag, String msg, Throwable tr) {
		Log.i(tag, msg, tr);
	}

	public void w(String tag, String msg) {
		Log.w(tag, msg);
	}

	public void w(String tag, String msg, Throwable tr) {
		Log.w(tag, msg, tr);
	}

}
