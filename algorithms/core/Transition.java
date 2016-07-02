package core;

public class Transition {
	private String begin, through, end;
	public Transition(String line){
		String[] parts = line.split(" ");
		begin = parts[0];
		through = parts[1];
		end = parts[2];
	}
	
	public void print(){
		System.out.println(begin+"---"+through+"--->"+end);
	}	
	
	public String generateDotCode(){
		return begin+" -> "+end+" [label="+through+"];";
	}
	
	public String getEnd(){
		return end;
	}
	
	public String getBegin(){
		return begin;
	}
	
	public String getEndFrom(String state){
		if(state.equals(begin)){
			return end;
		}
		else return "";
	}
	
	public String getConditionalEndFrom(String state,String symbol){
		if(state.equals(begin) && symbol.equals(through)){
			return end;
		}
		else return "";
	}
	
	public String getBeginFrom(String state){
		if(state.equals(end)){
			return begin;
		}
		else return "";
	}
	
	public boolean contains(String state){
		if(begin.equals(state) || end.equals(state)){
			return true;
		}
		else {
			return false;
		}
	}
}
