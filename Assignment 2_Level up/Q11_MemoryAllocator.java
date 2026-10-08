import java.util.HashMap;
import java.util.Map;

public class Q11_MemoryAllocator {

    static class Block {
        int start, size;
        Block next;
        Block(int start, int size) { this.start = start; this.size = size; }
    }

    private Block freeHead;
    private Map<String, Block> allocated = new HashMap<>();

    public Q11_MemoryAllocator(int totalSize) {
        this.freeHead = new Block(0, totalSize);
    }

    public boolean allocate(String id, int size) {
        Block curr = freeHead, prev = null;
        while (curr != null) {
            if (curr.size >= size) {
                Block alloc = new Block(curr.start, size);
                allocated.put(id, alloc);
                if (curr.size == size) {
                    if (prev == null) freeHead = curr.next;
                    else prev.next = curr.next;
                } else {
                    curr.start += size;
                    curr.size -= size;
                }
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    public void free(String id) {
        Block block = allocated.remove(id);
        if (block == null) return;

        Block prev = null, curr = freeHead;
        while (curr != null && curr.start < block.start) {
            prev = curr;
            curr = curr.next;
        }

        if (prev == null) {
            block.next = freeHead;
            freeHead = block;
        } else {
            block.next = prev.next;
            prev.next = block;
        }

        if (block.next != null && block.start + block.size == block.next.start) {
            block.size += block.next.size;
            block.next = block.next.next;
        }

        if (prev != null && prev.start + prev.size == block.start) {
            prev.size += block.size;
            prev.next = block.next;
        }
    }

    public void printFreeList() {
        Block curr = freeHead;
        System.out.print("Free: ");
        while (curr != null) {
            System.out.print("[" + curr.start + ".." + (curr.start + curr.size - 1) + "] ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Q11_MemoryAllocator mem = new Q11_MemoryAllocator(100);
        mem.allocate("A", 30);
        mem.allocate("B", 20);
        mem.allocate("C", 10);
        mem.free("B");
        mem.printFreeList();
    }
}