package br.com.creditas.kotlinspringsample.models

import java.io.Serializable
import java.util.*
import javax.persistence.*


@Embeddable
data class PostTagId(
    @Column(name = "post_id")
    val postId: UUID,

    @Column(name = "tag_id")
    val tagId: UUID
) : Serializable


@Entity(name = "PostTag")
@Table(name = "post_tag")
data class PostTag(
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
    var post: Post? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    var tag: Tag? = null
) {

    @EmbeddedId
    val id: PostTagId = PostTagId(post!!.id!!, tag!!.id!!)

    @Column(name = "created_on")
    val createdOn = Date()
}


@Entity(name = "Tag")
@Table(name = "tag")
data class Tag(val name: String) {

    @Id
    @GeneratedValue
    val id: UUID? = null

    @OneToMany(mappedBy = "tag", cascade = [CascadeType.ALL], orphanRemoval = true)
    val posts: MutableList<PostTag> = mutableListOf()

}


@Entity(name = "Post")
@Table(name = "post")
data class Post(val title: String) {

    @Id
    @GeneratedValue
    val id: UUID? = null

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    val tags: MutableList<PostTag> = mutableListOf()

    fun addTag(tag: Tag) {
        val postTag = PostTag(this, tag)
        tags.add(postTag)
        tag.posts.add(postTag)
    }

    fun removeTag(tag: Tag) {
        val iterator = tags.iterator()
        while (iterator.hasNext()) {
            val postTag = iterator.next()

            if (postTag.post == this && postTag.tag == tag) {
                iterator.remove()
                postTag.tag?.posts?.remove(postTag)
                postTag.post = null
                postTag.tag = null
            }
        }
    }

}