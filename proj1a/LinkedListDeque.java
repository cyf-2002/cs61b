public class LinkedListDeque<T> {
    //创建节点
    private class IntNode {
        private IntNode prev;
        private T item;
        private IntNode next;

        public IntNode(T i, IntNode n, IntNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private int size;
    private IntNode sentinel;

    //创建空链表
    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    //拷贝
    public LinkedListDeque(LinkedListDeque<T> other) {
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
        for (int i = 0; i < other.size(); i++) {
            addLast(other.get(i));
            size += 1;
        }

    }

    //链表头添加数据
    public void addFirst(T item) {
        sentinel.next.prev = new IntNode(item, sentinel.next, sentinel);
        sentinel.next = sentinel.next.prev;
        size += 1;
    }

    //链表尾添加数据
    public void addLast(T item) {
        sentinel.prev.next = new IntNode(item, sentinel, sentinel.prev);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    //判断是否为空
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    //获取链表长度
    public int size() {
        return size;
    }

    //打印链表
    public void printDeque() {
        IntNode p = sentinel;
        while (p.next != sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    //从头部移除数据
    public T removeFirst() {
        if (!isEmpty()) {
            T item = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return item;
        }
        return null;
    }

    //从尾部移除数据
    public T removeLast() {
        if (!isEmpty()) {
            T item = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;
            return item;
        }
        return null;
    }

    //返回指定下标的item
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        IntNode p = sentinel;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.item;
    }

    //递归获取指定下标的item
    private T getRecursiveHelper(IntNode a, int i) {
        if (i == 0) {
            return a.next.item;
        } else {
            a = a.next;
            i -= 1;
        }
        return getRecursiveHelper(a, i);
    }

    public T getRecursive(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        IntNode p = sentinel;
        return getRecursiveHelper(p, index);
    }

}
