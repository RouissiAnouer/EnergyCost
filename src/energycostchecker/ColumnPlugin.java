package energycostchecker;

public class ColumnPlugin {
	public String _filepath;
	public String _filewarning;
	public String _filepack;
	public String _type;
	public int _Line;
	
	public ColumnPlugin(String _path,String _warning,String _pack,int _line) {
		_filepath=_path;
		_filepack=_pack;
		_filewarning=_warning;
		_Line=_line;
	}
	public ColumnPlugin(String type,String _path,String _warning,int _line,String _pack) {
		_type=type;
		_filepath=_path;
		_filepack=_pack;
		_filewarning=_warning;
		_Line=_line;
	}
	
	
	

}
