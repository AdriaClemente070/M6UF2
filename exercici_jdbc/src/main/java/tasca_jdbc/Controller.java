package tasca_jdbc;

import java.sql.Connection;
import java.util.List;

public class Controller {
	Connection conn;

	public static void main(String[] args) { 
		System.out.println("Current directory (Exercici_JDBC/exercici_jdbc/database.xml):Exercici_JDBC/exercici_jdbc/database.xml" + System.getProperty("user.dir"));
		Controller controller = new Controller();
		controller.init();
	}

	private void init() {

		this.conn = ConnectionManager.getConnection("exercici_jdbc/database.xml");
		MainView mainView = new MainView();

		AtletaPgDAO atletaDAO = new AtletaPgDAO(conn);
		EsportPgDAO esportDAO = new EsportPgDAO(conn);

		// mainView.mainMenu()
		int option = mainView.mainMenu();
		// switch option
		switch (option) {
			case 1:
					mainView.llistaAtletes(atletaDAO.getAll());
				break;
			
			case 2:
					Atleta nouAtleta = mainView.atletaForm();
					boolean correcte = atletaDAO.add(nouAtleta);
					if (correcte == true){
						System.out.println("Atleta afegit correctament!!");
					}else{
						System.out.println("El atleta no s'ha afegit");
					}
				
			case 3:
					mainView.llistaEsport(esportDAO.getAll());
				break;

			case 4:
					Esport nouEsport = mainView.esportForm();
					boolean correcteEsport = esportDAO.add(nouEsport);
					if (correcteEsport == true){
						System.out.println("Esport afegit correctament!!");
					}else{
						System.out.println("El esport no s'ha afegit");
					}
				break;

			case 5:
					int codEsport = mainView.codEsport();
					List<Atleta> filtrats = atletaDAO.getAllChosen(codEsport);
					mainView.llistaAtletesChosen(filtrats);
				break;

			default:
				break;
		}




	}
}
