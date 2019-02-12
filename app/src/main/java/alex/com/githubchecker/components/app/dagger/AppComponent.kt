package alex.com.githubchecker.components.app.dagger

import alex.com.githubchecker.components.app.api.APIClient
import alex.com.githubchecker.components.app.data.DataManager
import android.content.Context
import dagger.Component
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by Alex on 11/12/2017.
 */

@AppScope
@Component(modules = [AppModule::class, NetworkModule::class, DataModule::class])
interface AppComponent {
    fun provideAPIClient(): APIClient

    fun provideDataManager(): DataManager

    fun provideContext(): Context
}
