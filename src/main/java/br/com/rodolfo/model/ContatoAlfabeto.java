package br.com.rodolfo.model;

public enum ContatoAlfabeto {

	A("A"), B("B"), C("C"), D("D"), E("E"), F("F"), G("G"), H("H"), I("I"), J("J"), K("K"),
	L("L"), M("M"), N("N"), O("O"), P("P"), Q("Q"), R("R"), S("S"), T("T"), U("U"), V("V"),
	X("X"), Y("Y"), Z("Z");
	
	private String alfabeto;
	
	ContatoAlfabeto(String alfabeto) {
		this.alfabeto = alfabeto;
	}
	
	
	public String getAlfabeto () {
		return this.alfabeto;
	}
}
