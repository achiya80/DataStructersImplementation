package SpecialDataStructures;

import DataStructures.MaxHeap;

import java.util.LinkedList;
import java.util.List;

public class LawyersCompany {


    MaxHeap<Employee>[] departments;

    int[] wageBonus;

    int[] sizes;

    public LawyersCompany(int N, Employee[] S){
        MaxHeap.Node[][] arr = new MaxHeap.Node[10][N];
        wageBonus = new int[10];
        departments = new MaxHeap[10];
        sizes = new int[10];
        MaxHeap<Employee> mh = new MaxHeap<Employee>();
        for (Employee e : S) {
            MaxHeap<Employee>.Node<Employee> node = mh.createNode(e.fee, e);
            arr[e.department-1][sizes[e.department-1]] = node;
            sizes[e.department-1]++;
        }
        for(int i = 0;i < 10;i++){
            departments[i] = new MaxHeap(arr[i], sizes[i]);
        }
    }

    public void increaseSalary(int d, int r){
        wageBonus[d-1] += r;
    }

    public void insert(String name, int f, int d){
        Employee e = new Employee(name, f-wageBonus[d-1], d);
        insert(e);
    }

    private void insert(Employee e){
        departments[e.department-1].Insert(e.fee, e);
        sizes[e.department-1]++;
    }

    public Employee fireTop(){
        Employee max = new Employee("", Integer.MIN_VALUE, 1);
        for (int i = 0;i < 10;i++){
            if(employeeFee(max) <= employeeFee(departments[i].Maximum().getData())){
                max = departments[i].Maximum().getData();
            }
        }
        sizes[max.department-1]--;
        return departments[max.department-1].ExtractMax().getData();
    }

    public List<Employee> shoeTop5(){
        List<Employee> list = new LinkedList<>();
        MaxHeap<Employee> mh = new MaxHeap<>(new MaxHeap.Node[50], 0);
        for(int i = 0;i< 10;i++){
            for(MaxHeap<Employee>.Node<Employee> e : departments[ i].returnTopC(5)){
                mh.Insert(e.getId() + wageBonus[e.getData().department-1], e.getData());
            }
        }
        for (int i = 0;i < 5;i++){
            list.add(mh.ExtractMax().getData());
        }
        return list;
    }


    public Employee getEmployee(String name, int fee, int department){
        return new Employee(name, fee, department);
    }

    public class Employee{
        int fee;
        int department;
        String name;

        Employee(String name, int fee, int department){
            this.name = name;
            this.fee = fee;
            this.department = department;
        }

        public String toString(){
            return "Name: " + name + "   Fee: " + fee + "   Department: " + department;
        }
    }


    private int employeeFee(Employee e){
        return e.fee + wageBonus[e.department-1];
    }
}
