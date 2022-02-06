package com.unitbv.ubisoft;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLData {
    private Context context;
    private Game [] data;

    // constructor
    public XMLData(Context c) {
        this.context = c;

        // parse games.xml to construct data
        InputStream stream = this.context.getResources().openRawResource(R.raw.games);
        DocumentBuilder builder = null;
        Document xmlDoc = null;

        try
        {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmlDoc = builder.parse(stream);
        }
        catch (Exception e ) {}

        // chop xmlDoc for tags id, name, etc.
        NodeList idList = xmlDoc.getElementsByTagName("id");
        NodeList nameList = xmlDoc.getElementsByTagName("name");
        NodeList releaseDateList = xmlDoc.getElementsByTagName("release_date");
        NodeList imageList = xmlDoc.getElementsByTagName("image");
        NodeList galleryList = xmlDoc.getElementsByTagName("gallery");
        NodeList urlList = xmlDoc.getElementsByTagName("url");
        NodeList shortDescriptionList = xmlDoc.getElementsByTagName("short_description");
        NodeList longDescriptionList = xmlDoc.getElementsByTagName("long_description");

        // traverse these lists to extract id, name, etc.
        data = new Game[nameList.getLength()];

        for(int i=0; i<data.length; i++) {
            String id = idList.item(i).getFirstChild().getNodeValue();
            String name = nameList.item(i).getFirstChild().getNodeValue();
            String releaseDate = releaseDateList.item(i).getFirstChild().getNodeValue();
            String image = imageList.item(i).getFirstChild().getNodeValue();
            String gallery = galleryList.item(i).getFirstChild().getNodeValue();
            String url = urlList.item(i).getFirstChild().getNodeValue();
            String shortDescription = shortDescriptionList.item(i).getFirstChild().getNodeValue();
            String longDescription = longDescriptionList.item(i).getFirstChild().getNodeValue();

            data[i] = new Game(id, name, releaseDate, image, gallery, url, shortDescription, longDescription);
        }
    }

    // some get methods
    public int getLength() { return data.length; }
    public int getFranchiseLength(String id) {
        int count = 0;
        for(int i=0;i<getLength();i++) {
            if(getGame(i).getId().equals(id))
                count++;
        }

        return count;
    }
    public Game[] getFranchiseGame(String id){
        Game[] games = new Game[getFranchiseLength(id)];
        for(int i=0, j=0; i<data.length; i++) {
            if (getGame(i).getId().equals(id)) {
                games[j++] = getGame(i);
            }
        }

        return games;
    }
    public Game getGame(int i) { return data[i]; }
    public Game getGameFromFranchise(String id, int index) {
        Game[] franchise = new Game[getFranchiseLength(id)];

        for(int i=0, j=0; i<data.length; i++) {
            if (getGame(i).getId().equals(id)) {
                franchise[j++] = getGame(i);
            }
        }

        return franchise[index];
    }
    public String[] getGameNames() {
        String[] names = new String[getLength()];

        for(int i=0; i<names.length; i++)
            names[i] = getGame(i).getName();

        return names;
    }
    public String[] getGameFranchise(String id) {
        String[] franchise = new String[getFranchiseLength(id)];

        for(int i=0, j=0; i<data.length; i++) {
            if (getGame(i).getId().equals(id)) {
                franchise[j++] = getGame(i).getName();
            }
        }

        return franchise;
    }
    public String[] parseGallery(Game game) {
        String[] galleryPhotos;
        String gallery = game.getGallery();
        galleryPhotos = gallery.split("\\s+");

        return galleryPhotos;
    }
}

