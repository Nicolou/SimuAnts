
public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Starting ants simulation");
		
		model.SpaceField sp = new model.SpaceField();
		sp.setDimX(800);sp.setDimY(600);
		model.Ant ant1 = new model.Ant(sp);
		ant1.setName("fourmis 1");
		ant1.setPosx(400);ant1.setPosy(300);
		ant1.simu();
		model.OldAnt ant2 = new model.OldAnt(sp);
		ant2.setName("toto");
		ant2.setPosx(200);ant2.setPosy(100);
		ant2.simu();
		
		
		view.Window w = new view.Window();
		w.setLand(sp);
		
		w.setVisible(true);
		
	}

}
