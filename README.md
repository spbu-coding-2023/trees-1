# 🌳 Derev'ja 🌳

Derev'ja is a small kotlin library which provides simple yet powerful interface for working with different binary search trees. This projects implements many trees and adds tools to easily store and manipulate data inside bst.
## Installation

Building from source with

```bash
  ./gradlew build
```

## Usage Examples
### Create
```kotlin
val tree1 = RegularTree<Int, String>()
val tree2 = AVLTree<Double, Int>()
val tree3 = RedBlackTree<Int, Int>()
```
### Insert
```kotlin
tree.insert(23, "A")
```
### Remove
```kotlin
val removedValue = tree.remove(23) // "A"
```
### Search
```kotlin
val value1 = tree.search(23) // "A"
```
### Traverse
```kotlin
val keys = tree.traverse(InOrder()) { it.key }
val values = tree.traverse(LevelOrder()) { it.value }
```
## References

| Element                                                                       | Description                                                                                                          |
|-------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------|
| `RegularTree<K : Comparable<K>, V>()`                                         | Creates a new instance of a Regular Binary Search Tree.                                                              |
| `AVLTree<K : Comparable<K>, V>()`                                             | Creates a new instance of a AVL Binary Search Tree.                                                                  |
| `RedBlackTree<K : Comparable<K>, V>()`                                        | Creates a new instance of a Red Black Binary Search Tree.                                                            |
| `search(key: K): V?`                                                          | Search a value in the tree. returns value, if no such value exists returns null.                                     |
| `insert(key: K): R`                                                           | Insert a new value by key in the tree. returns node.                                                                 |
| `remove(key: K): V?`                                                          | Removes a value by key. returns removed value, if no such value exists returns null.                                 |
| `traverse<T>(traversal: BSTTraversal, extractionFunction: (R) -> T): List<T>` | Traverses the tree according to traversal, use extractionFunction to get the data from node. returns list of values. |
| `PreOrder()`                                                                  | Performs a pre-order traversal of the tree.                                                                          |
| `PostOrder()`                                                                 | Performs a post-order traversal of the tree.                                                                         |
| `InOrder()`                                                                   | Performs a in-order traversal of the tree.                                                                           |
| `LevelOrder()`                                                                | Performs a level order traversal of the tree.                                                                        |


## Contributing

Follow the steps to add your changes to the project:
- Create a fork of this repository
- Clone repository
- Create new branch named feature/<name>
- Add your changes in that branch
- Push your branch to repository
- Open a pull request to this project



## Authors

- [kinokotakenoko9](https://www.github.com/kinokotakenoko9)
- [Mukovenkov-Roman-Sergeyevich](https://www.github.com/Mukovenkov-Roman-Sergeyevich)
- [mshipilov5](https://www.github.com/mshipilov5)
## License

[MIT](LICENSE)

