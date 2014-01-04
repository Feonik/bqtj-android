package com.BibleQuote.bqtj.android.utils;

import android.content.Context;
import com.BibleQuote.bqtj.CoreContext;
import com.BibleQuote.bqtj.utils.*;
import android.util.Xml.Encoding;
import com.BibleQuote.bqtj.android.R;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UpdateManagerAndroid extends UpdateManager {

	private Context context;

	public UpdateManagerAndroid(Context context) {
		super();
		this.context = context;
	}


	@Override
	protected void updateBuiltInModules() {
		try {
			saveBuiltInModule("bible_rst.zip", R.raw.bible_rst);
			saveBuiltInModule("bible_kjv.zip", R.raw.bible_kjv);
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
		}
	}

	private void saveBuiltInModule(String fileName, int rawId) throws IOException {
		File moduleDir = new File(DataConstants.FS_MODULES_PATH);
		InputStream moduleStream = context.getResources().openRawResource(rawId);
		OutputStream newModule = new FileOutputStream(new File(moduleDir, fileName));
		byte[] buf = new byte[1024];
		int len;
		while ((len = moduleStream.read(buf)) > 0) {
			newModule.write(buf, 0, len);
		}
		moduleStream.close();
		newModule.close();
	}

	@Override
	protected void saveTSK() {

		// TODO работа с TSK в DataConstants ?

		try {
			InputStream tskStream = context.getResources().openRawResource(R.raw
					.tsk);
			ZipInputStream zStream = new ZipInputStream(tskStream);

			InputStreamReader isReader = null;
			ZipEntry entry;
			while ((entry = zStream.getNextEntry()) != null) {
				String entryName = entry.getName().toLowerCase();
				if (entryName.contains(File.separator)) {
					entryName = entryName.substring(entryName.lastIndexOf(File.separator) + 1);
				}
				if (entryName.equalsIgnoreCase("tsk.xml")) {
					isReader = new InputStreamReader(zStream, Encoding.UTF_8.toString());
					break;
				}
				;
			}
			if (isReader == null) {
				return;
			}
			BufferedReader tsk_br = new BufferedReader(isReader);

			File tskFile = new File(DataConstants.APP_DATA_PATH, "tsk.xml");
			if (tskFile.exists()) {
				tskFile.delete();
			}
			BufferedWriter tsk_bw = new BufferedWriter(new FileWriter(tskFile));

			char[] buf = new char[1024];
			int len;
			while ((len = tsk_br.read(buf)) > 0) {
				tsk_bw.write(buf, 0, len);
			}
			tsk_bw.flush();
			tsk_bw.close();
			tsk_br.close();
		} catch (FileNotFoundException e) {
			Log.e(TAG, e.getMessage());
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
		}
	}
}
