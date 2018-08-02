package cn.scu.petcommunity.apiTest;

import java.util.*;

/**
 遍历
 */
public class Main {
    Map<Integer, Integer> res = new HashMap<>();// id - cost
    public static void main(String[] args) {

        ArrayList<Integer> _ids = new ArrayList<Integer>();
        ArrayList<Integer> _parents = new ArrayList<Integer>();
        ArrayList<Integer> _costs = new ArrayList<Integer>();

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while(line != null && !line.isEmpty()) {
            if(line.trim().equals("0")) break;
            String []values = line.trim().split(" ");
            if(values.length != 3) {
                break;
            }
            _ids.add(Integer.parseInt(values[0]));
            _parents.add(Integer.parseInt(values[1]));
            _costs.add(Integer.parseInt(values[2]));
            line = in.nextLine();
        }
        int res = resolve(_ids, _parents, _costs);

        System.out.println(String.valueOf(res));
    }

    // 树的xianxu遍历
    public static int resolve(ArrayList<Integer> ids, ArrayList<Integer> parents, ArrayList<Integer> costs) {
        res(ids,parents,costs,0);
        Collections.sort(costs);
        return costs.get(0);
    }

    private static void res(ArrayList<Integer> ids, ArrayList<Integer> parents, ArrayList<Integer> costs,Integer id){
        if(id != null && id >= 0 && costs.get(id) >= 0){//存在父任务
            costs.set(id,costs.get(id) + costs.get(parents.get(id)));
        }
        res(ids, parents,costs,parents.get(id));
        res(ids, parents,costs,parents.get(id));
    }

}