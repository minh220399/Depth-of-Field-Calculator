package com.example.androidepthoffield.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LensManager  implements Iterable<Len>{
    List<Len> lens = new ArrayList<>();
    private static LensManager manager;
    private LensManager()
    {
        lens = new ArrayList<Len>();
    }
    public static LensManager getInstance()
    {
        if (manager == null)
        {
            manager = new LensManager();
        }
        return manager;
    }
    public Len getLens (int num)
    {
        return lens.get(num);
    }

    public void add(Len len){
        lens.add(len);
    }
    public void printLensDetail(){
        for (int i = 0; i <lens.size(); i++){
            System.out.println(i + ". "+ lens.get(i).lenInfor());
        }
    }

    public int size(){
        return lens.size();
    }
    public double getMaximumAperture(int index){
        return lens.get(index).getMaximumAperture();
    }
    @Override
    public Iterator<Len> iterator() {
        return lens.iterator();
    }
}
