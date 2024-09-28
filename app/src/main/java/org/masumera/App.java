/*
 * This source file was generated by the Gradle 'init' task
 */
package org.masumera;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.masumera.body.Album;
import org.masumera.body.DataSong;
import org.masumera.body.Song;
import org.masumera.service.QueryApiClient;
import org.masumera.service.SaveFile;
import org.masumera.service.TransformData;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;




@SuppressWarnings("unused")
public class App {

  private static final Logger logger = LogManager.getLogger("test");
    public static void main(String[] args) {

      logger.info("INFO message");
      logger.error("ERROR message");
/*
// new try to implement another api service method
    try {
    
    String result = query.queryApi("Viva la vida", "coldplay");
    System.out.println(result);

    SaveFile saveFile = new SaveFile();
    saveFile.sampleFile(result);
    
    } catch (InterruptedException | IOException e){
      System.out.println("error");
    }
    */
      QueryApiClient queryApiClient = new QueryApiClient();

      Scanner scanner = new Scanner(System.in);
      System.out.println("Escribe el nombre de la cancion");

      String querySong;
      if (scanner.hasNextLine()) {
        querySong = scanner.nextLine();
      }else {
        System.out.println("No se encontro linea, se usara la cancion viva la vida");
        querySong = "wannabe";
      }
      try {
        List<Song> songLists = queryApiClient.searchTrackId(querySong);
        String songTrack = queryApiClient.searchQuerySongId(songLists);
      
        // String songTrack = queryApiClient.searchTrackId(querySong);
        JsonNode trackDetail = queryApiClient.getTrack(songTrack);
  
        // System.out.println(trackDetail.toPrettyString());  
      //
      // } catch (Exception e) {
      //   System.out.println("Error: " + e.getMessage());
      // }

       TransformData transformData = new TransformData(queryApiClient);
    // try {
      // String songId = queryApiClient.searchTrackId(songTrack);
      // System.out.println(songTrack);
      String jsonResult = transformData.getTrackAsString(songTrack);
      // System.out.println(jsonResult);
      DataSong dataSong = transformData.getData(jsonResult, DataSong.class);
      Song song = new Song(dataSong);
      System.out.println(song.getName());
      System.out.println(song.getId());
      System.out.println(song.getArtist());
      System.out.println(song.getAlbumName().getName());

      System.out.println(song.toString());
      
      
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
    
      scanner.close();
    }
}
