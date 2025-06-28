package io.github.ilikeyourhat.happinesstracker

import android.content.Context
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import io.github.ilikeyourhat.happinesstracker.di.AppGraph
import io.github.ilikeyourhat.happinesstracker.domain.db.DatabaseBuilderProvider

@DependencyGraph(AppScope::class)
interface AndroidAppGraph : AppGraph {

    @Provides
    private fun providesDatabaseBuilder(
        impl: DatabaseBuilderProviderImpl
    ) : DatabaseBuilderProvider = impl

    @DependencyGraph.Factory
    fun interface Factory {
        fun create(@Provides context: Context): AndroidAppGraph
    }
}
