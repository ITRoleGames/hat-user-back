package rubber.dutch.hat.domain.model

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.util.ProxyUtils
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity<T> {

    @CreatedDate
    @Column(updatable = false, nullable = false)
    lateinit var created: Instant

    @LastModifiedDate
    @Column(nullable = false)
    lateinit var modified: Instant

    abstract val id: T
    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as BaseEntity<*>

        return this.id != null && this.id == other.id
    }

    override fun hashCode() = this.id.hashCode()

    override fun toString(): String {
        return "${this.javaClass.simpleName} (id=${this.id})"
    }
}