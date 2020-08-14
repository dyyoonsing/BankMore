package kr.ac.kopo.util;

import java.io.File;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class KopoFileNamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File f) {
		String name = f.getName();
		String ext = "";
		int dot = name.lastIndexOf(".");
		if (dot != -1) {
			ext = name.substring(dot); 
		} else {
			ext = "";
		}
		//32비트의 중복되지 않는 난수 생성 UUID.randomUUID()
		String str = "kopo" + UUID.randomUUID();
		File renameFile = new File(f.getParent(), str + ext);
		return renameFile;
	}
}
