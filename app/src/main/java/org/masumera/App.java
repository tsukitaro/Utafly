/*
 * This source file was generated by the Gradle 'init' task
 */
package org.masumera;

import java.io.IOException;
import java.util.Scanner;

import org.masumera.body.Album;
import org.masumera.body.DataSong;
import org.masumera.body.Song;
import org.masumera.service.CallRequest;
import org.masumera.service.QueryApiClient;
import org.masumera.service.SaveFile;
import org.masumera.service.TransformData;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@SuppressWarnings("unused")
public class App {
    public static void main(String[] args) {
    //
    // Song song = new Song("fireworks", 2, 5);
    // // song.setName("fire");
    // Song hero = new Song("mirai", 3, 8);
    //
    // System.out.println(song.getName());
    // System.out.println(song.getDuration());
    //
    // Album album = new Album(hero, "ella");
    //
    // System.out.println(album.getSong().getName());
    // System.out.println(album.getName());
    // 
    
    // CallRequest query = new CallRequest();
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
        String songTrack = queryApiClient.searchTrackId(querySong);
        JsonNode trackDetail = queryApiClient.getTrack(songTrack);
  
        // System.out.println(trackDetail.toPrettyString());  

      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }

       TransformData transformData = new TransformData(queryApiClient);
    try {
      String songId = queryApiClient.searchTrackId(querySong);
      String jsonResult = transformData.getTrackAsString(songId);
      System.out.println(jsonResult);
      DataSong dataSong = transformData.getData(jsonResult, DataSong.class);
      Song song = new Song(dataSong);
      System.out.println(song.getName());
      System.out.println(song.getArtist());
      System.out.println(song.getAlbumName().getName());
      
      
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
    
      scanner.close();
    }
}
