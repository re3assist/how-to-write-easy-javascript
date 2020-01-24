package filelist;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * ルートディレクトリ直下のディレクトリとその配下のファイルリストをjsonで出力します。
 * @author marine
 *
 */
public class App {

	private static String ROOT_DIRECTORY = "D:\\00_project";

	public static void main(String[] args) {

		JSONArray directoryList = new JSONArray();

		for (File directory : new File(ROOT_DIRECTORY).listFiles()) {
			if (directory.isDirectory()) {

				JSONObject jObj = new JSONObject();
				jObj.put("name", directory.getName());

				JSONArray fileList = new JSONArray();
				for (File files : new File(directory.toString()).listFiles()) {
					if (files.isFile()) {
						fileList.put(files.getName());
					}
				}

				jObj.put("fileList", fileList);
				directoryList.put(jObj);
			}

		}

		System.out.println(directoryList.toString());
		josnWrite("let directories = "+ directoryList.toString(4));
	}

	/**
	 * Jsonファイルを出力します。
	 * @param jsonData
	 */
	private static void josnWrite (String jsonData) {
        File file = new File("./directories.json");

        try(PrintWriter printWriter = new PrintWriter(new BufferedWriter
            (new OutputStreamWriter(new FileOutputStream(file), "UTF-8")));) {
        	printWriter.println(jsonData);

        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }

	}
}
