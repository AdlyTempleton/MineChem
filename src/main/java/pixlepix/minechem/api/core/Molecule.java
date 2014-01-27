package pixlepix.minechem.api.core;

import org.jetbrains.annotations.NotNull;

public class Molecule extends Chemical {

	public EnumMolecule molecule;

	public Molecule(EnumMolecule molecule, int amount) {
		super(amount);
		this.molecule = molecule;
	}

	@NotNull
	@Override
	public Chemical copy() {
		return new Molecule(molecule, amount);
	}

	;

	public Molecule(EnumMolecule molecule) {
		super(1);
		this.molecule = molecule;
	}

	@Override
	public boolean sameAs(Chemical chemical) {
		return chemical instanceof Molecule && ((Molecule) chemical).molecule == molecule;
	}

}
