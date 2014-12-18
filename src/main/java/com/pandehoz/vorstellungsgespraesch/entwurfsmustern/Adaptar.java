package com.pandehoz.vorstellungsgespraesch.entwurfsmustern;

class GifImage{}
class JpgImage{}

class LegacyJPGPhotoAlbum{
	public void add(JpgImage image){
		System.out.println("JPG Image added");
	}
}

class LegacyGIFInternet{
	public void add(GifImage image){
		System.out.println("Gif image added");
	}
}

interface SaviourGIFAdaptar{
	public void add(GifImage image);
}

class JPGPhotoAlbum implements SaviourGIFAdaptar{

	private LegacyJPGPhotoAlbum album = new LegacyJPGPhotoAlbum();
	
	private JpgImage getJPGImage(GifImage image){
		//convert from gif to jpg
		return null;
	}
	
	public void add(GifImage image) {
		album.add(getJPGImage(image));
	}
	
}

class GIFInternet implements SaviourGIFAdaptar{

	private LegacyGIFInternet internet = new LegacyGIFInternet();
	public void add(GifImage image) {
		internet.add(image);
	}
	
}

//Problem statement : User only has gif images to upload, hence can add to internet but not to the photo album
//SaviourAdaptar comes to the rescue with an interface that helps the user add to album
public class Adaptar {

	public static void main(String[] args){
		GifImage gif = new GifImage();
		new GIFInternet().add(gif);
		new JPGPhotoAlbum().add(gif);
	}
}
