package it.andclaval.tetris.tool;

public class Couple<T> {
	private T first;
	private T second;
	
	public Couple(T x, T y){
		this.first = x;
		this.second = y;
	}
	
	public T getFirst() {
		return first;
	}
	
	public void setFirst(T first) {
		this.first = first;
	}
	
	public void setCouple(T first, T second){
		this.first = first;
		this.second = second;
	}
	
	public T getSecond() {
		return second;
	}
	
	public void setSecond(T second) {
		this.second = second;
	}
	
	public boolean equals(Object object){
		Couple<T> tmp = (Couple<T>) object;
		return this.first.equals(tmp.getFirst()) && this.second.equals(tmp.getSecond());
	}
	
	public String toString(){
		return "(" + this.first + ", " + this.second + ")";
	}
	
}
