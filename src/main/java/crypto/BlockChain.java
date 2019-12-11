package crypto;


import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@ToString
class Block {
    private int previousHash;
    private String data;
    private int hash;
    public Block(String data, int previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        // Mix the content of this block with previous hash to create the hash of this new block
        // and that's what makes it block chain
        this.hash  = Arrays.hashCode(new Integer[]{data.hashCode(), previousHash});
    }
}


public class BlockChain {

    public static void main(String[] args) {
        List<Block> blockChainList =  new ArrayList<>();
        Block genesis = new Block("BlockChain", 0);
        blockChainList.add(genesis);
        Block helloBlock = new Block("Hello", blockChainList.get(blockChainList.size()-1).getHash());
        blockChainList.add(helloBlock);
        Block worldBlock = new Block("World", blockChainList.get(blockChainList.size()-1).getHash());
        blockChainList.add(worldBlock);
        Block dZoneBlock = new Block("DZone", blockChainList.get(blockChainList.size()-1).getHash());
        blockChainList.add(dZoneBlock);
        System.out.println("---------------------");
        System.out.println("- BlockChain -");
        System.out.println("---------------------");
        blockChainList.forEach(System.out::println);
        System.out.println("---------------------");
        System.out.println("Is valid?: " + validate(blockChainList));
        System.out.println("---------------------");
        // corrupt block chain by modifying one of the block
        Block hiBlock = new Block("Hi", genesis.getHash());
        int index = blockChainList.indexOf(helloBlock);
        blockChainList.remove(index);
        blockChainList.add(index, hiBlock);
        System.out.println("Corrupted block chain by replacing 'Hello' block with 'Hi' Block");
        System.out.println("---------------------");
        System.out.println("- BlockChain -");
        System.out.println("---------------------");
        blockChainList.forEach(System.out::println);
        System.out.println("---------------------");
        System.out.println("Is valid?: " + validate(blockChainList));
        System.out.println("---------------------");
    }
    private static boolean validate(List<Block> blockChain) {
        boolean result = true;
        Block lastBlock = null;
        for(int i = blockChain.size() -1; i >= 0; i--) {
            if(lastBlock == null) {
                lastBlock = blockChain.get(i);
            }
            else {
                Block current = blockChain.get(i);
                if(lastBlock.getPreviousHash() != current.getHash()) {
                    result = false;
                    break;
                }
                lastBlock = current;
            }
        }
        return result;
    }
}
