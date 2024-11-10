package de.telran.lesson_12_05nov;
// Реализуйте методы:
// find(value)
// insert(value)
// delete(value)
// print(node)

public class BinaryTree {
    private Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public BinaryTree() {
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void print() {
        System.out.println(root);
    }

    public void insert(int value) {
        Node newNode = new Node();
        newNode.setValue(value);

        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent = null;

            while (true) {
                parent = current;
                if (value == current.getValue()) { // такой узел уже есть
                    return;
                } else if (value < current.getValue()) {
                    current = current.getLeft();
                    if (current == null) {
                        parent.setLeft(newNode);
                        return;
                    }
                } else {
                    current = current.getRight();
                    if (current == null) {
                        parent.setRight(newNode);
                        return;
                    }
                }
            }
        }
    }

    // вставка через рекурсию
    public Node insertRecursive(Node current, Node newNode) {
        if (root == null) {
            root = newNode;
            return newNode;
        }
        if (current == null) {
            return newNode;
        }

        if (newNode.getValue() < current.getValue()) {
            current.setLeft(insertRecursive(current.getLeft(), newNode));
        } else if (newNode.getValue() > current.getValue()) {
            current.setRight(insertRecursive(current.getRight(), newNode));
        } else {
            return current;
        }
        return current;
    }

    public Node find(int value) {
        Node current = root; // начинаем поиск с корневого узла

        while (current.getValue() != value) { // поиск пока не будет найден элемент или не будут перебраны все
            if (current.getValue() > value) { // движение влево?
                current = current.getLeft();
            } else {
                current = current.getRight(); //движение вправо
            }
            if (current == null) { // если потомка нет,
                return null;
            }
        }
        return current; // возвращаем найденный элемент
    }
    //  Задание 1. Закончить реализацию методов класса BinaryTree,
    //  которую мы делали в классе:   delete(value)


    public void delete(int value) {
        Node current = root;
        Node parent = null;

        // 1. Поиск узла, который нужно удалить
        while (current != null && current.getValue() != value) {
            parent = current;
            if (value < current.getValue()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        if (current == null) {  // Если узел не найден, ничего не делаем
            return;
        }

        // 2: Если узел найден, проверяем количество потомков

        // Случай 1: У узла нет потомков (листовой узел)
        if (current.getLeft() == null && current.getRight() == null) {
            if (current == root) {
                root = null; // Удаляем корень, если он был единственным узлом
            } else if (parent.getLeft() == current) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        }

        // Случай 2: У узла один потомок
        else if (current.getLeft() == null || current.getRight() == null) {
            Node child = (current.getLeft() != null) ? current.getLeft() : current.getRight();

            if (current == root) {
                root = child; // Заменяем корень на единственного потомка
            } else if (parent.getLeft() == current) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }

        // Случай 3: У узла два потомка
        else {
            // Ищем минимальный узел в правом поддереве (наименьший узел больше текущего)
            Node childOfParent = current;
            Node child_2 = current.getRight();

            while (child_2.getLeft() != null) {
                childOfParent = child_2;
                child_2 = child_2.getLeft();
            }

            // Заменяем значение удаляемого узла значением наследника
            current.setValue(child_2.getValue());

            // Удаляем наследника
            if (childOfParent.getLeft() == child_2) {
                childOfParent.setLeft(child_2.getRight());
            } else {
                childOfParent.setRight(child_2.getRight());
            }
        }
    }
}



