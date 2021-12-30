public class ArrayDeque {
    int[] items;
    int size;
    int head;
    int tail;

    public ArrayDeque() {
        items = new int[8];
        size = 0;
        //第一位元素
        head = 0;
        //下一个尾部元素插入的位置
        tail = 0;
    }

    /**
     * 取模运算，得到逻辑上的下标
     */
    private int getMod(int logicIndex) {
        if (logicIndex < 0) {
            logicIndex += items.length;
        }
        if (logicIndex >= items.length) {
            logicIndex -= items.length;
        }

        return logicIndex;
    }

    /**
     * Inserts X into the first of the list.
     */
    public void addFirst(int x) {
        head = getMod(head - 1);
        items[head] = x;
        size += 1;
        if (head == tail) {
            resizemax();
        }
    }

    /**
     * Inserts X into the back of the list.
     */
    public void addLast(int x) {
        items[tail] = x;
        tail = getMod(tail + 1);
        size += 1;
        if (head == tail) {
            resizemax();
        }
    }

    //判断是否为空
    public boolean isEmpty() {
        return head == tail;
    }

    //获取链表长度
    public int size() {
        return size;
    }

    //打印链表
    public void printDeque() {
        if (head < tail) {
            for (int i = head; i < tail; i++) {
                System.out.print(items[i] + " ");
            }
            System.out.println();
        } else {
            for (int i = head; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < tail; i++) {
                System.out.print(items[i] + " ");
            }
            System.out.println();
        }
    }

    //从头部移除数据
    public int removeFirst() {
        int item = items[head];
        items[head] = 0;
        head = getMod(head + 1);
        size -= 1;
        if (size <= items.length / 2) {
            resizemin();
        }

        return item;
    }

    //从尾部移除数据
    public int removeLast() {
        int item = items[tail];
        items[tail] = 0;
        tail = getMod(tail - 1);
        size -= 1;
        if (size < items.length / 2) {
            resizemin();
        }

        return item;
    }

    //返回指定下标的item
    public int get(int index) {
        return items[index];
    }

    /**
     * 调整数组大小
     */
    private void resizemax() {
        int[] newitems = new int[items.length * 2];
        if (head < tail) {
            System.arraycopy(items, head, newitems, 0, size);

        } else {
            int j = 0;
            for (int i = head; i < items.length; i++) {
                newitems[j++] = items[i];
            }
            for (int i = 0; i < tail - 1; i++) {
                newitems[j++] = items[i];
            }

        }
        tail = size;
        head = 0;
        items = newitems;

    }

    private void resizemin() {
        int[] newitems = new int[items.length / 2];
        if (head < tail) {
            System.arraycopy(items, head, newitems, 0, size);

        } else {
            int j = 0;
            for (int i = head; i < items.length; i++) {
                newitems[j++] = items[i];
            }
            for (int i = 0; i < tail; i++) {
                newitems[j++] = items[i];
            }

        }
        tail = size;
        head = 0;
        items = newitems;

    }

    /**
     * 复制
     */
    public ArrayDeque(ArrayDeque other) {
        items = new int[other.items.length];
        head = other.head;
        tail = other.tail;
        size = other.size;
        if (other.head < other.tail) {
            System.arraycopy(other.items, other.head, items, other.head, other.tail - other.head);
        } else {
            System.arraycopy(other.items, other.head, items, other.head, other.items.length - other.head);
            System.arraycopy(other.items, 0, items, 0, other.tail);
        }

    }

}
