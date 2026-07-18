package com.ems.controller.admin;

import com.ems.model.LeaveRequest;
import com.ems.service.LeaveService;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ApproveLeavesController {

    @FXML private TableView<LeaveRequest> leaveTable;
    @FXML private TableColumn<LeaveRequest, Integer> colLeaveId;
    @FXML private TableColumn<LeaveRequest, String> colEmpName;
    @FXML private TableColumn<LeaveRequest, String> colStart;
    @FXML private TableColumn<LeaveRequest, String> colEnd;
    @FXML private TableColumn<LeaveRequest, String> colReason;
    @FXML private TableColumn<LeaveRequest, String> colStatus;
    @FXML private Label statusLabel;

    private final LeaveService leaveService = new LeaveService();

    @FXML
    public void initialize() {
        colLeaveId.setCellValueFactory(new PropertyValueFactory<>("leaveId"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colStart.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        refreshTable();
    }

    private void refreshTable() {
        leaveTable.setItems(FXCollections.observableArrayList(leaveService.getAll()));
    }

    private LeaveRequest selected() {
        LeaveRequest lr = leaveTable.getSelectionModel().getSelectedItem();
        if (lr == null) statusLabel.setText("Select a leave request first.");
        return lr;
    }

    @FXML
    private void handleApprove() {
        LeaveRequest lr = selected();
        if (lr == null) return;
        leaveService.approve(lr.getLeaveId());
        statusLabel.setText("Leave #" + lr.getLeaveId() + " approved.");
        refreshTable();
    }

    @FXML
    private void handleReject() {
        LeaveRequest lr = selected();
        if (lr == null) return;
        leaveService.reject(lr.getLeaveId());
        statusLabel.setText("Leave #" + lr.getLeaveId() + " rejected.");
        refreshTable();
    }
}
