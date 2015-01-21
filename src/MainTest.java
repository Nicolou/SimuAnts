
public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Starting ants simulation");
		
		model.SpaceField sp = new model.SpaceField();
		sp.setDimX(800);sp.setDimY(600);
		
		for (int i=0; i<40; i++) {
			model.Ant ant1 = new model.Ant(sp);
			ant1.setName("fourmis_" + i);
			ant1.setPosx(100);ant1.setPosy(100);
			ant1.simu();			
		}

		for (int i=0; i<5 ; i++) {
			model.OldAnt ant2 = new model.OldAnt(sp);
			ant2.setName("toto");
			ant2.setPosx(50);ant2.setPosy(50);
			ant2.simu();
		}
		
		view.Window w = new view.Window();
		w.setLand(sp);
		
		w.setVisible(true);
		
	}

}
