package com.kostikum.lifehackstudio.network

data class NetworkCompany(
    val id: String,
    val name: String,
    val img: String
)

data class NetworkCompanyDetails(
    val id: String,
    val name: String,
    val img: String,
    val description: String,
    val lat: String,
    val lot: String,
    val www: String,
    val phone: String
)

////fun NetworkCompaniesContainer.asDomainModel(): List<Video> {
////    return videos.map {
////        Video(
////            title = it.title,
////            description = it.description,
////            url = it.url,
////            updated = it.updated,
////            thumbnail = it.thumbnail
////        )
////    }
////}
////
////fun NetworkCompaniesContainer.asDatabaseModel(): List<DatabaseVideo> {
////    return videos.map {
////        DatabaseVideo(
////            title = it.title,
////            description = it.description,
////            url = it.url,
////            updated = it.updated,
////            thumbnail = it.thumbnail
////        )
////    }
//}
