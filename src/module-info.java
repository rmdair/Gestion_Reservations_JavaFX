module Projet_Agence_Voyage_Eclipse {
	requires java.sql;
	requires java.desktop;
	requires javafx.controls;
	requires javafx.base;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.media;
	requires javafx.web;
	
	exports fx to javafx.graphics;
}