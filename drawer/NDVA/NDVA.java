package NDVA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NDVA {
	private String[] states;
	private String[] abc;
	private String[] begin_states;
	private String[] end_states;
	private ArrayList<Transition> transitions;
	public NDVA(File f){
		try (
		    InputStream fis = new FileInputStream(f);
		    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		    BufferedReader br = new BufferedReader(isr);
		) {
			states = br.readLine().split(" ");
			abc = br.readLine().split(" ");
			begin_states = br.readLine().split(" ");
			end_states = br.readLine().split(" ");
			transitions = new ArrayList<Transition>();
			String line;
		    while ((line = br.readLine()) != null) {
		        transitions.add(new Transition(line));
		    }
		}
		catch(IOException ex){
			System.out.println("File read error!");
		}
	}
	
	public String[] getBeginStates(){
		return begin_states;
	}
	
	public void generateDotCode(String filename){
		BufferedWriter output = null;
        try {
            File file = new File(filename+".gv");
            output = new BufferedWriter(new FileWriter(file));
            output.write("digraph G{");
            output.write("ranksep=0.5;");
            output.write("nodesep=0.5;");
            output.write("rankdir=LR;");
            output.write("node [shape=\"circle\",fontsize=\"16\"];");
            output.write("fontsize=\"10\";");
            output.write("compound=true;");
            for(int i = 0; i<end_states.length; i++){
            	output.write(end_states[i]+" [shape=doublecircle];");
            }
            for(int i = 0; i<begin_states.length; i++){
            	 output.write("i"+i+" [shape=point, style=invis];");
            	 output.write("i"+i+" -> "+begin_states[i] +" ");
            }
            for(Transition tr:transitions){
            	output.write(tr.generateDotCode());
            }
            output.write("}");
            output.close();
        } catch ( IOException e ) {
            System.out.println("File writing error!!!");
        }
	}
	
	public void eliminateUnreachableStates(){
		ArrayList<String> reachableStates = new ArrayList<String>(); 
		for(int i = 0; i < begin_states.length ; i++){
			reachableStates.add(begin_states[i]);
		}
		int lengthBefore;
		do{
			lengthBefore = reachableStates.size();
			ArrayList<String> toAdd = new ArrayList<String>(); 
			for(String state:reachableStates){
				for(String s: reachableStatesFrom(state)){
					if(!toAdd.contains(s)){
						toAdd.add(s);
					}
				}
			}	
			for(String state: toAdd){
				if(!reachableStates.contains(state)){
					reachableStates.add(state);
				}
			}
		}while(lengthBefore < reachableStates.size());
		ArrayList<Transition> toRemove = new ArrayList<>();
		for(Transition tr:transitions){
			if(reachableStates.contains(tr.getBegin()) && reachableStates.contains(tr.getEnd())){	
			}
			else {
				toRemove.add(tr);
			}
		}
		transitions.removeAll(toRemove);
		states = new String[reachableStates.size()];
		int k = 0;
		for(String state:reachableStates){
			states[k++] = state;
		}
		ArrayList<String> newEndStates = new ArrayList<>();
		for(String state :end_states){
			if(reachableStates.contains(state)){
				if(!newEndStates.contains(state)){
					newEndStates.add(state);
				}
			}
		}
		k = 0;
		end_states = new String[newEndStates.size()];
		for(String state:newEndStates){
			end_states[k++] = state;
		}
 	}
	
	public ArrayList<String> reachableStatesFrom(String state){
		ArrayList<String> reachableStates = new ArrayList<String>();
		for(Transition tr:transitions){
			String toAdd = tr.getEndFrom(state);
			if(!toAdd.isEmpty()){
				if(!reachableStates.contains(toAdd)){
					reachableStates.add(toAdd);
				}
			}
		}
		return reachableStates;
	}
	
	public void eliminateUnproductiveStates(){
		ArrayList<String> reachableStates = new ArrayList<String>(); 
		for(int i = 0; i < end_states.length ; i++){
			reachableStates.add(end_states[i]);
		}
		int lengthBefore;
		do{
			lengthBefore = reachableStates.size();
			ArrayList<String> toAdd = new ArrayList<String>(); 
			for(String state:reachableStates){
				for(String s: reachableStatesTo(state)){
					if(!toAdd.contains(s)){
						toAdd.add(s);
					}
				}
			}	
			for(String state: toAdd){
				if(!reachableStates.contains(state)){
					reachableStates.add(state);
				}
			}
		}while(lengthBefore < reachableStates.size());
		ArrayList<Transition> toRemove = new ArrayList<>();
		for(Transition tr:transitions){
			if(reachableStates.contains(tr.getBegin()) && reachableStates.contains(tr.getEnd())){	
			}
			else {
				toRemove.add(tr);
			}
		}
		transitions.removeAll(toRemove);
		states = new String[reachableStates.size()];
		int k = 0;
		for(String state:reachableStates){
			states[k++] = state;
		}
		ArrayList<String> newBeginStates = new ArrayList<>();
		for(String state :begin_states){
			if(reachableStates.contains(state)){
				if(!newBeginStates.contains(state)){
					newBeginStates.add(state);
				}
			}
		}
		k = 0;
		begin_states = new String[newBeginStates.size()];
		for(String state:newBeginStates){
			begin_states[k++] = state;
		}
	}
	
	private ArrayList<String> reachableStatesTo(String state){
		ArrayList<String> reachableStates = new ArrayList<String>();
		for(Transition tr:transitions){
			String toAdd = tr.getBeginFrom(state);
			if(!toAdd.isEmpty()){
				if(!reachableStates.contains(toAdd)){
					reachableStates.add(toAdd);
				}
			}
		}
		return reachableStates;
	}
	
	public ArrayList<Transition> getTransitions(){
		return transitions;
	}
	
	public boolean isEndState(String s){
		for(int i = 0;i<end_states.length;i++){
			if(s.equals(end_states[i])){
				return true;
			}
		}
		return false;
	}
	public int autoMultiplicity(String state){
		int m = 0;
		for(Transition t:transitions){
			if(t.getBegin().equals(state) && t.getEnd().equals(state)){
				m++;
			}
		}
		return m;
	}
	public Map<String,Integer> getMultiplicityMap(){
		Map<String,Integer> m = new HashMap<>();
		for(int i = 0;i<begin_states.length;i++){
			m.put(begin_states[i], autoMultiplicity(begin_states[i]));
		}
		for(int i = 0;i<end_states.length;i++){
			m.put(end_states[i], autoMultiplicity(end_states[i]));
		}
		for(int i = 0;i<states.length;i++){
			m.put(states[i], autoMultiplicity(states[i]));
		}
		return m;
	}
	
	public ArrayList<String> getAllStates(){
		ArrayList<String> reachableStates = new ArrayList<String>();
		for(String s:begin_states){
				if(!reachableStates.contains(s)){
					reachableStates.add(s);
				}
			}
		
		for(String s:end_states){
			if(!reachableStates.contains(s)){
				reachableStates.add(s);
			}
		}

		for(String s:states){
			if(!reachableStates.contains(s)){
				reachableStates.add(s);
			}
		}
		return reachableStates;
	}
}
