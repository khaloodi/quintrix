package com.example.wk9_hw

val commonData: CommonData = CommonData()

class CommonData {

    public fun getImageUrls(): Array<String> {
        return this.imageUrls
    }

    public fun getImageUrlAtIndex(index: Int): String {

        return (if (index > -1 && index < 11) {
                        this.imageUrls[index]
                    } else {
                        this.imageUrls[0]
                    }
                )
    }

    public fun getImageDescriptions(): Array<String> {
        return this.imageDescriptions
    }

    public fun getImageDescriptionAtIndex(index : Int) : String {
        return(if (index > -1 && index < 11) {
                        this.imageDescriptions[index]
                    }else {
                        this.imageDescriptions[0]
                    }
                )
    }

    private val imageUrls: Array<String> = arrayOf(
        "https://images.unsplash.com/photo-1650738443805-39f24f02b1c6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3174&q=80",
        "https://images.unsplash.com/photo-1650737066575-d09bd5e5faa3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=988&q=80",
        "https://images.unsplash.com/photo-1650906495627-3295a04fdf89?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDV8NnNNVmpUTFNrZVF8fGVufDB8fHx8&auto=format&fit=crop&w=800&q=60",
        "https://images.unsplash.com/photo-1650575185249-2b6a65f92d6c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDl8NnNNVmpUTFNrZVF8fGVufDB8fHx8&auto=format&fit=crop&w=800&q=60",
        "https://images.unsplash.com/photo-1650795434397-0d1b5961681c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDE4fDZzTVZqVExTa2VRfHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=800&q=60",
        "https://images.unsplash.com/photo-1650564365557-b987ac5d219e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDI1fDZzTVZqVExTa2VRfHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=800&q=60",
        "https://images.unsplash.com/photo-1650400716131-12a410e12975?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDM2fDZzTVZqVExTa2VRfHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=800&q=60",
        "https://images.unsplash.com/photo-1650494685588-12bd76b2b1e8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDQ2fDZzTVZqVExTa2VRfHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=800&q=60",
        "https://images.unsplash.com/photo-1645231889371-815eaa660f19?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDQ1fDZzTVZqVExTa2VRfHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=800&q=60",
        "https://images.unsplash.com/photo-1649530870643-5a1c442d609b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDE0Mnw2c01WalRMU2tlUXx8ZW58MHx8fHw%3D&auto=format&fit=crop&w=800&q=60"
    )

    private val imageDescriptions: Array<String> = arrayOf(
        "sand at night",
        "a bird",
        "a bee",
        "cacti",
        "blue flowers",
        "sunset",
        "snow",
        "horse",
        "sea lion",
        "pink flowers"
    )

}