package com.BibleQuote.bqtj.android;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import com.BibleQuote.bqtj.CoreContext;
import com.BibleQuote.bqtj.android.utils.UpdateManagerAndroid;
import com.BibleQuote.bqtj.utils.DataConstants;
import com.BibleQuote.bqtj.utils.UpdateManager;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Nikita K.
 * Date: 15.11.13
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */

public final class CoreContextAndroid extends CoreContext {

	private static CoreContextAndroid coreContextAndroid;

	private Context context;

	private CoreContextAndroid(Context context) {
		super();
		this.context = context;
	}

	public static synchronized CoreContextAndroid getCoreContextAndroid(
			Context context) {

		if (coreContextAndroid == null) {
			coreContextAndroid = new CoreContextAndroid(context);
		}

		return coreContextAndroid;
	}

	@Override
	protected UpdateManager getUpdateManager() {
		return new UpdateManagerAndroid(context);
	}

	@Override
	public String getAppVersionName() {
		try {
			return context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionName;
		} catch (PackageManager.NameNotFoundException e) {
			return "0.00.01";
		}
	}

	@Override
	public int getAppVersionCode() {
		try {
			return context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			return 0;
		}
	}

	@Override
	public String getAppDataPath() {

		return Environment.getExternalStorageState().equals(Environment
				.MEDIA_MOUNTED)

				? Environment.getExternalStorageDirectory()
						+ File.separator + DataConstants.APP_DIR_NAME

				: Environment.getDataDirectory()
						+ File.separator
						// system name of dir /data/data  (2nd data)
						+ "data"
						+ File.separator + DataConstants.APP_PACKAGE_DIR_NAME;
	}

	@Override
	public File getSystemCacheDir() {
		return context.getCacheDir();
	}

}
