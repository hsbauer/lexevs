/*
 * Copyright: (c) 2004-2010 Mayo Foundation for Medical Education and 
 * Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
 * triple-shield Mayo logo are trademarks and service marks of MFMER.
 *
 * Except as contained in the copyright notice above, or as used to identify 
 * MFMER as the author of this software, the trade names, trademarks, service
 * marks, or product names of the copyright holder shall not be used in
 * advertising, promotion or otherwise in connection with this software without
 * prior written authorization of the copyright holder.
 * 
 * Licensed under the Eclipse Public License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * 
 * 		http://www.eclipse.org/legal/epl-v10.html
 * 
 */
package org.LexGrid.LexBIG.gui.valueSetsView;

import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.gui.LB_VSD_GUI;
import org.LexGrid.LexBIG.gui.displayResults.VDDisplayFilterResult;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ValueSetDefValueSetDefNameFilter {
    private Shell shell_;
    private LB_VSD_GUI lb_vsd_gui_;
    
    public ValueSetDefValueSetDefNameFilter(LB_VSD_GUI lb_vsd_gui, Shell parent) {
        shell_ = new Shell(parent.getDisplay());
        shell_.setText("Value Set Definition Name Filter");
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 6;
        shell_.setLayout(gridLayout);
        shell_.setSize(800, 600);
        Rectangle bds = shell_.getDisplay().getBounds();
        Point p = shell_.getSize();
        int nLeft = (bds.width - p.x) / 2;
        int nTop = (bds.height - p.y) / 2;
        shell_.setBounds(nLeft, nTop, p.x, p.y);
        
        lb_vsd_gui_ = lb_vsd_gui;
        
        new Label(shell_, SWT.NONE).setText("Value Set Definition Name : ");
        
        final Text vsdNameText = new Text(shell_, SWT.SINGLE | SWT.BORDER);
        GridData gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
        gridData.horizontalSpan = 5;
        vsdNameText.setLayoutData(gridData);
        vsdNameText.setFocus();
        
        Button cancelButton = new Button(shell_, SWT.PUSH);
        cancelButton.setText("CANCEL");
        gridData = new GridData(GridData.END, GridData.CENTER, false, false);
        gridData.horizontalSpan = 2;
        cancelButton.setLayoutData(gridData);
        cancelButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                shell_.close();
                shell_.dispose();
            }
        });
        
        Button filterButton = new Button(shell_, SWT.PUSH);
        filterButton.setText("Filter");
        gridData = new GridData(GridData.END, GridData.CENTER, false, false);
        gridData.horizontalSpan = 1;
        filterButton.setLayoutData(gridData);
        filterButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                displayResult(vsdNameText.getText());
            }
        });
        
        shell_.addDisposeListener(new DisposeListener() {
            
            public void widgetDisposed(DisposeEvent arg0) {
                // TODO Auto-generated method stub
                
            }
        });
        
        shell_.pack();
        shell_.open();
    }    
    
    private void displayResult(String vsdName){
        java.util.List<String> uris = null;
        try {
            uris = lb_vsd_gui_.getValueSetDefinitionService().listValueSetDefinitions(vsdName);
        } catch (LBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        new VDDisplayFilterResult(lb_vsd_gui_, uris);
    }
}