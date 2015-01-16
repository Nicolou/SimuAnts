
public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Starting ants simulation");
		
		model.SpaceField sp = new model.SpaceField();
		sp.setDimX(100);sp.setDimY(100);
		model.Ant ant1 = new model.Ant(sp);
		ant1.setName("fourmis 1");
		ant1.setPosx(50);ant1.setPosy(50);
		ant1.simu();
		
		view.Window w = new view.Window();
		w.setLand(sp);
		
		w.setVisible(true);
		
	}

}
