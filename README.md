# 🌳 Derev'ja 🌳

Derev'ja is a small kotlin library which provides simple yet powerful interface for working with different binary search trees. This projects implements many trees and adds tools to easily store and manipulate data inside bst.
## Installation

Download from [Releases](https://github.com/spbu-coding-2023/trees-1/releases) or build your own from source with

```bash
  ./gradlew build
```

## Usage Examples
### Create
Create instance from available tree class and define types for keys and values 
```kotlin
val tree1 = RegularTree<Int, String>()
val tree2 = AVLTree<Double, Int>()
val tree3 = RedBlackTree<Int, Int>()
```
### Insert
```kotlin
tree.put(23, "Apple")
```
or 
```kotlin
tree[23] = "Apple"
```
### Remove
```kotlin
// returns "Apple"
tree.remove(23)
```
### Search
```kotlin
// returns "Apple"
tree.search(23)
```
or
```kotlin
// returns "A"
tree[23] 
```
### Traverse
By specifying traversal type and callback function tree can be traversed in almost any way you like
```kotlin
// traverses keys inorder
val preOrderKeys = tree.traversed.preOrder { it.key }

// traverses values in level order
val levelOrderValues = tree.traversed.levelOrder { it.value } 
```
## Documentation

For detailed documentation and all methods, check out our [Wiki](https://github.com/spbu-coding-2023/trees-1/wiki) 

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
