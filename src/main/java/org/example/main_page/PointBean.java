package org.example.main_page;

import org.example.database.DatabaseHelper;
import org.example.model.PointEntry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class PointBean implements Serializable {
    private static final long serialVersionUID = 0xDEAD;

    private DatabaseHelper databaseHelper;
    private ArrayList<PointEntry> points;

    private Integer x;
    private Double y;
    private Integer r;

    public void addPointIfCorrect() throws SQLException {
        System.out.println(this.x);
        System.out.println(this.y);
        System.out.println(this.r);
        PointEntry pointEntry = new PointEntry(x, y, r, new Date().toString(), false);
        pointEntry.setHit(pointEntry.checkHit());
        databaseHelper.save(pointEntry);
        points.add(0, pointEntry);
    }

    public PointBean() throws SQLException {
        databaseHelper = new DatabaseHelper();
        points = databaseHelper.getAll();
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public ArrayList<PointEntry> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<PointEntry> points) {
        this.points = points;
    }
}
