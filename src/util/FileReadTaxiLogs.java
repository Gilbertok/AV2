package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.TaxiCorrida;

public class FileReadTaxiLogs {
	
	private static List<TaxiCorrida> taxisLogs = new ArrayList<>();
	
	private FileReadTaxiLogs() {}
	
	public static List<TaxiCorrida> getRegistrosLogs() {
		new FileReadTaxiLogs().buscaDadosLogs();
		System.out.println("Objetos Criados: "+ taxisLogs.size());
		return taxisLogs;
	}
	
	private void buscaDadosLogs() {
		int i = 0;
		File afile[] = this.openFileDirectory();
		for (int j = afile.length; i < j; i++) {
			if (taxisLogs.size() < 10000) {
				File arquivo = afile[i];
				this.getLogsFromFile(arquivo);
			} else {
				break;
			}
		}
	}

	private File[] openFileDirectory() {
		File file = new File("release/taxi_log");
		System.out.println("Arquivos listados: "+ file.listFiles().length);
		return file.listFiles();
	}
	
	@SuppressWarnings("resource")
	private void getLogsFromFile(File arquivo) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Scanner scanner = new Scanner(new FileReader(arquivo.getPath())).useDelimiter("\\,|\\n");
			while(scanner.hasNext()) {
				String id = scanner.next();
				String dataCorrida = scanner.next();
				String longitude = scanner.next();
				String latitude = scanner.next();
				TaxiCorrida taxi = new TaxiCorrida(Integer.parseInt(id), formatter.parse(dataCorrida), Double.parseDouble(longitude), Double.parseDouble(latitude));
				taxisLogs.add(taxi);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
