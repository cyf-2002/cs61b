public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int head;
    private int tail;

    public ArrayDeque() {
        items = (T[]) new Object[8];
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
    public void addFirst(T x) {
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
    public void addLast(T x) {
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
    public T removeFirst() {
        if (!isEmpty()) {
            T item = items[head];
            items[head] = null;
            head = getMod(head + 1);
            size -= 1;
            if (size <= items.length / 2) {
                resizemin();
            }
            return item;
        }
        return null;
    }

    //从尾部移除数据
    public T removeLast() {
        if (!isEmpty()) {
            int newtail = getMod(tail - 1);
            T item = items[newtail];
            items[tail] = null;
            tail = newtail;
            size -= 1;
            if (size < items.length / 2) {
                resizemin();
            }
            return item;
        }
        return null;
    }

    //返回指定下标的item
    public T get(int index) {
        return items[index];
    }

    /**
     * 调整数组大小
     */
    private void resizemax() {
        T[] newitems = (T[]) new Object[items.length * 2];

        int j = 0;
        for (int i = head; i < items.length; i++) {
            newitems[j++] = items[i];
        }
        for (int i = 0; i < head; i++) {
            newitems[j++] = items[i];
        }

        tail = size;
        head = 0;
        items = newitems;

    }

    private void resizemin() {
        T[] newitems = (T[]) new Object[items.length / 2];
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
    public ArrayDeque(ArrayDeque<T> other) {
        items = (T[]) new Object[other.items.length];
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
