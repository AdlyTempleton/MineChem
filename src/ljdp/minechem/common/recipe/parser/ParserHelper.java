package ljdp.minechem.common.recipe.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.google.common.base.Enums;
import com.google.common.base.Optional;

import ljdp.minechem.api.core.Element;
import ljdp.minechem.api.core.EnumElement;
import ljdp.minechem.api.core.EnumMolecule;
import ljdp.minechem.api.core.Molecule;

public class ParserHelper {
	public String[] parseElementStrings(String elementString) {
		ArrayList<String> elements = new ArrayList<String>();
		String temp = "";
		for(int i = 0; i < elementString.length(); i++) {
			char c = elementString.charAt(i);
			if(temp != "" && (c == '#' || Character.isUpperCase(c))) { // New element
				elements.add(temp);
				temp = "" + c;
			}
			temp += c;
		}
		return elements.toArray(new String[elements.size()]);
	}
	
	public Object[] parseElements(String elementString) {
		String[] elementStrings = parseElementStrings(elementString);
		Object[] elements = new Object[elementStrings.length];
		for(int i = 0; i < elementStrings.length; i++) {
			String el = elementStrings[i];
			int amount = 1;
			// Parse away number
			String digitNumber = "";
			while(Character.isDigit(el.charAt(el.length()-1))) {
				digitNumber = el.charAt(el.length()-1) + digitNumber;
				el = el.substring(0, el.length()-1);
			}
			if(digitNumber.length() > 0) amount = Integer.parseInt(digitNumber);
			if(el.startsWith("#")) {
				if(el.startsWith("#m_")) { // Molecules
					String moleculeName = el.substring(3);
					Optional<EnumMolecule> molecule = Enums.getIfPresent(EnumMolecule.class, el);
					if(molecule.isPresent()) {
						elements[i] = new Molecule(molecule.get(), amount);
					}
				}
			} else {
				Optional<EnumElement> element = Enums.getIfPresent(EnumElement.class, el);
				if(element.isPresent()) {
					elements[i] = new Element(element.get(), amount);
				}
			}
		}
		return elements;
	}
}
