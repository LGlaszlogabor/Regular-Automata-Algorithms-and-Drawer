package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import NDVA.NDVA;
import NDVA.Transition;
import core.BeginState;
import core.Connection;
import core.EndState;
import core.NDVAPanel;
import core.State;

public class NDVADrawer extends JFrame{
	private static final long serialVersionUID = 1L;
	private static final int W = 800;
	private static final int H =600;
	private NDVA ndva;
	private NDVAPanel panel;
	private ArrayList<String> drawn;
	private Map<String,State> map;
	public NDVADrawer(NDVA ndva){
		this.ndva = ndva;
		drawn = new ArrayList<>();
		map = new HashMap<>();
	}
	
	public void draw(){
		setBounds(100,100, W,H);
		panel = new NDVAPanel();
		String[] begin_states = ndva.getBeginStates();
		BeginState b;
		ArrayList<String> list ;
		for(int i = 0 ; i<begin_states.length;i++){
			if(!drawn.contains(begin_states[i])){
				if(ndva.isEndState(begin_states[i]))b = new BeginState(40,(i+1)*H/(begin_states.length+1),begin_states[i],true);
				else b = new BeginState(40,(i+1)*H/(begin_states.length+1),begin_states[i]);
				list = ndva.reachableStatesFrom(begin_states[i]);
				list.remove(begin_states[i]);
				drawn.add(begin_states[i]);
				map.put(begin_states[i], b);
				panel.add(b);
			}
		}
		for(int i = 0 ; i<begin_states.length;i++){
			list = ndva.reachableStatesFrom(begin_states[i]);
			drawFurther(120, i*H/(begin_states.length+1),(i+1)*H/(begin_states.length+1), list);
		}
		ArrayList<String> diff = ndva.getAllStates();
		diff.removeAll(drawn);
		State bb;
		for(int i = 0 ; i<diff.size();i++){
			if(!drawn.contains(diff.get(i))){
			if(ndva.isEndState(diff.get(i))){;
			bb = new EndState(W-40,(i+1)*H/(diff.size()+1),diff.get(i));
			}
			else {
				bb = new State(W-40,(i+1)*H/(diff.size()+1),diff.get(i));
			}
			drawn.add(diff.get(i));
			panel.add(bb);
			map.put(diff.get(i), bb);
			list = ndva.reachableStatesFrom(diff.get(i));
			list.remove(diff.get(i));
			list.removeAll(drawn);
			list = ndva.reachableStatesFrom(diff.get(i));
			drawFurtherBack(W-120, i*H/(begin_states.length+1),(i+1)*H/(begin_states.length+1), list);
			}
		}
		drawConnections();
		add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void drawFurther(int level,int hmin,int hmax,ArrayList<String> states){
		if(states.isEmpty()) return;
		else{
			ArrayList<String> list ;
			State b;
			for(int i = 0;i<states.size();i++){
				if(!drawn.contains(states.get(i))){
					if(ndva.isEndState(states.get(i))){;
						b = new EndState(level,hmin+(i+1)*(hmax-hmin)/states.size() ,states.get(i));
					}
					else {
						b = new State(level,hmin+(i+1)*(hmax-hmin)/states.size() ,states.get(i));
					}
					drawn.add(states.get(i));
					panel.add(b);
					map.put(states.get(i), b);
					list = ndva.reachableStatesFrom(states.get(i));
					list.remove(states.get(i));
					list.removeAll(drawn);
					drawFurther(level+80, hmin+(i)*(hmax-hmin)/states.size(),hmin+(i+1)*(hmax-hmin)/states.size(), list);
				}
			}
		}
	}
	
	private void drawFurtherBack(int level,int hmin,int hmax,ArrayList<String> states){
		if(states.isEmpty()) return;
		else{
			ArrayList<String> list ;
			State b;
			for(int i = 0;i<states.size();i++){
				if(!drawn.contains(states.get(i))){
					if(ndva.isEndState(states.get(i))){;
						b = new EndState(level,hmin+(i+1)*(hmax-hmin)/states.size() ,states.get(i));
					}
					else {
						b = new State(level,hmin+(i+1)*(hmax-hmin)/states.size() ,states.get(i));
					}
					drawn.add(states.get(i));
					panel.add(b);
					map.put(states.get(i), b);
					list = ndva.reachableStatesFrom(states.get(i));
					list.remove(states.get(i));
					list.removeAll(drawn);
					drawFurther(level-80, hmin+(i)*(hmax-hmin)/states.size(),hmin+(i+1)*(hmax-hmin)/states.size(), list);
				}
			}
		}
	}
	
	private void drawConnections(){
		ArrayList<Transition> ts = ndva.getTransitions();
		State from, to;
		Map<String,Integer> m = ndva.getMultiplicityMap();
		for(Transition t: ts){
			from  = map.get(t.getBegin());
			to = map.get(t.getEnd());
			panel.add(new Connection(from,to,t.getThrough(),m.get(t.getBegin())));
			m.put(t.getBegin(), m.get(t.getBegin())-1);
		}
	}
}
