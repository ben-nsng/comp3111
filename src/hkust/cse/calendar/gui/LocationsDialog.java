package hkust.cse.calendar.gui;


import java.awt.BorderLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import javax.swing.BoxLayout;

import javax.swing.DefaultListModel;

import javax.swing.JButton;

import javax.swing.JList;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.ListSelectionModel;

import hkust.cse.calendar.apptstorage.ApptStorageControllerImpl;

import javax.swing.JFrame;

import hkust.cse.calendar.unit.Location;

import javax.swing.JTextField;

import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class LocationsDialog extends JFrame {

	
	  private static final long serialVersionUID = 1L;

      private ApptStorageControllerImpl _controller;
     
      private DefaultListModel<Location> listModel;

      private JList<Location> list;

      private JTextField locNameText;
      
      private Location[] locationarray;

      public LocationsDialog(ApptStorageControllerImpl controller) {

    	  locationarray= new Location[0];

              _controller = controller;

              this.setLayout(new BorderLayout());

              this.setLocationByPlatform(true);

              this.setSize(300, 200);
    
              listModel = new DefaultListModel<Location>();      

              list = new JList<Location>(listModel);      

              list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   
              
              list.setSelectedIndex(0);      
              
//              list.addListSelectionListener(new ListSelectionListener(){
//
 //             });
              

   
              
      list.setVisibleRowCount(5);        

      JScrollPane listScrollPane = new JScrollPane(list);       

      JButton addButton = new JButton("Add");        

      addButton.addActionListener(new ActionListener(){

                      public void actionPerformed(ActionEvent arg0) {

                            //  Location[] locations = _controller.getLocationList();

                    	  Location[] locations = locationarray;
                    
                              for(int i = 0; i < locations.length; i++){

                                      if (locations[i].getName().equals(locNameText.getText())){

                                              JOptionPane.showMessageDialog(null, "Duplicate location" ,"INPUT ERROR", JOptionPane.ERROR_MESSAGE);

                                              return;

                                      }

                              }

                              listModel.addElement(new Location(locNameText.getText()));

                              saveLocations();

                      }

              

      });      

      

      addButton.setEnabled(true);      

      JButton removeButton = new JButton("Remove");       

      removeButton.addActionListener(new ActionListener(){
      

                      public void actionPerformed(ActionEvent arg0) {

                 
                              int index = list.getSelectedIndex();

                              if (index != -1) {

                                      listModel.removeElementAt(index);

                                      saveLocations();

                              }

                      }

      });    

    
      locNameText = new JTextField();


      JPanel buttonPane = new JPanel();      

      buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));    

      buttonPane.add(locNameText); 

      buttonPane.add(addButton);               

      buttonPane.add(removeButton);   

      
      buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));      

      add(listScrollPane, BorderLayout.CENTER);       

      add(buttonPane, BorderLayout.PAGE_END); 


      this.setVisible(true);

      loadLocations();

      }

      
      private void loadLocations() {

         //     Location[] locations = _controller.getLocationList();

    	  Location[] locations = locationarray;
              listModel.clear();

              if (locations != null) {

                      for(Location loc:locations) {

                              listModel.addElement(loc);

                      }

              }

      }


      private void saveLocations()

      {

              int count = listModel.getSize();

              Location[] locations = new Location[count];

              for (int i = 0; i < count; i++) {

                      locations[i] = (Location) listModel.elementAt(i);

              }

           //   _controller.setlocationList(locations);

              locationarray = locations;
          
      }

}