package developer.abdulaziz.traffic_laws.Classes

class User {
    var id: Int? = null
    var imagePath: String? = null
    var name: String? = null
    var about: String? = null
    var typeId: Int? = null
    var liked: Int? = null

    constructor(
        id: Int?,
        imagePath: String?,
        name: String?,
        about: String?,
        typeId: Int?,
        liked: Int?
    ) {
        this.id = id
        this.imagePath = imagePath
        this.name = name
        this.about = about
        this.typeId = typeId
        this.liked = liked
    }

    constructor(imagePath: String?, name: String?, about: String?, typeId: Int?, liked: Int?) {
        this.imagePath = imagePath
        this.name = name
        this.about = about
        this.typeId = typeId
        this.liked = liked
    }

    constructor()
}