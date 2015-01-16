
public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Starting ants simulation");
		
		model.SpaceField sp = new model.SpaceField();
		model.Ant ant1 = new model.Ant(sp);
		ant1.setName("fourmis 1");
		ant1.setPosx(200);ant1.setPosy(200);
		ant1.simu();
		
		view.Window w = new view.Window();
		w.setLand(sp);
		
		w.setVisible(true);
		
	}

}
