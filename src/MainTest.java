
public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Starting ants simulation");
		
		model.SpaceField sp = new model.SpaceField();
		sp.setDimX(800);sp.setDimY(600);
		
		int max1=40, max2=5;
		try {
			if (args.length>0) max1 = Integer.parseInt(args[0]);
			if (args.length>1) max2 =Integer.parseInt(args[1]);
		}
		catch (NumberFormatException  ex) {
			System.err.println("usage: parameter integer...");
		}
		
		for (int i=0; i<max1; i++) {
			model.Ant ant1 = new model.Ant(sp);
			ant1.setName("fourmis_" + i);
			ant1.setPosx(sp.getDimX()/2);ant1.setPosy(sp.getDimY()/2);
			ant1.simu();			
		}

		for (int i=0; i<max2 ; i++) {
			model.OldAnt ant2 = new model.OldAnt(sp);
			ant2.setName("toto");
			ant2.setPosx(sp.getDimX()/2);ant2.setPosy(sp.getDimY()/2);
			ant2.simu();
		}
		
		view.Window w = new view.Window();
		w.setLand(sp);
		
		w.setVisible(true);
		
	}

}
