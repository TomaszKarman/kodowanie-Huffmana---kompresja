import java.util.ArrayList;

/**
 * Created by TKK on 2016-12-28.
 */
public class Tree {
    Node top;
    ArrayList<CharAndCount> tempData=new ArrayList<>();
    ArrayList<Node> nodes = new ArrayList<>();

    public Node buildTree(ArrayList<CharAndCount> CandC) {

        for (CharAndCount aa : CandC) {
            tempData.add(new CharAndCount(aa.getName(), aa.getCounter()));
        }

        DataTable.sort(tempData);


        makeTree(tempData);
        top = nodes.get(nodes.size() - 1);

        return top;
    }

    public void makeTree(ArrayList<CharAndCount> list) {
        {
//            System.err.println("//////////////////////////////////////////////////////////");
            DataTable.sort(list);
//            for(CharAndCount ch:list){
//                System.err.println("Znak :" + ch.getName()+" wystapien: "+ch.getCounter());
//            }
            Node newNode1 = null;
            Node newNode2 = null;
            if (list.size() <= 1) {
                for (Node nn : nodes) {
                    if (nn.getNodeName().equals(list.get(0).getName())) {
                        top=nn;
                    }
                }
                return;
            } else {
                String newCharAndCountName = list.get(0).getName().toString() + list.get(1).getName().toString();
                int newCounter = list.get(1).getCounter() + list.get(0).getCounter();
                //boolean newStatus = tempData.get(0).isStatus() || tempData.get(1).isStatus();
                CharAndCount newChar = new CharAndCount(newCharAndCountName, newCounter);
                newChar.setStatus(true);

                //DataTable.sort(list);


                if (list.get(0).isStatus() == false) {
                    newNode1 = new Node(list.get(0).getName(), null, null, null, false);
                    nodes.add(newNode1);
                } else {

                    for (Node nn : nodes) {
                        if (nn.getNodeName().equals(list.get(0).getName())) {
                            newNode1 = nn;
                            break;
                        }
                    }
                }

                if (list.get(1).isStatus() == false) {
                    newNode2 = new Node(list.get(1).getName(), null, null, null, false);
                    nodes.add(newNode2);
                } else {
                    for (Node nn : nodes) {
                        if (nn.getNodeName() == list.get(1).getName()) {
                            newNode2 = nn;
                            break;
                        }
                    }
                }

                Node newNode3 = new Node(newCharAndCountName, newNode1, newNode2, null, false);
                newNode1.setParent(newNode3);
                newNode2.setParent(newNode3);


                nodes.add(newNode3);
                list.remove(1);
                list.remove(0);
                list.add(newChar);
                makeTree(list);
            }
        }
    }

    public void displayNodes() {
        for (Node nn : nodes) {
            System.out.println(nn.getNodeName().toString());
        }
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<CharAndCount> getTempData() {
        return tempData;
    }

    public Node getTop() {
        return top;
    }



    public String getCodeByChar (char charToCheck){
        Node newTop = top;
        String track = "";
        while(newTop.hasChildren()){
            newTop = newTop.newTop(charToCheck);
            //System.err.println(track);
            track = track + newTop.getParent().leftOrRight(newTop);
            }
        return track;
    }
}
