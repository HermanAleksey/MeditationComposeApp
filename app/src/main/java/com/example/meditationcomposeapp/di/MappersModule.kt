package com.example.meditationcomposeapp.di

import com.example.meditationcomposeapp.data_source.entity.db.BeerListItem
import com.example.punk_api.api.model.BeerResponse
import com.example.meditationcomposeapp.data_source.entity.network.LoginUserResponse
import com.example.meditationcomposeapp.data_source.entity.network.UpdateDescriptionResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.data_source.mappers.db.BeerDBMapper
import com.example.meditationcomposeapp.data_source.mappers.network.profile.ProfileMapper
import com.example.meditationcomposeapp.data_source.mappers.network.profile.UpdateDescriptionMapper
import com.example.meditationcomposeapp.data_source.mappers.network.punk.*
import com.example.meditationcomposeapp.model.entity.beer.*
import com.example.meditationcomposeapp.model.entity.login_flow.Profile
import com.example.meditationcomposeapp.model.entity.login_flow.UpdateDescriptionModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MappersModule {

    @Binds
    abstract fun provideUpdateDescriptionMapper(
        implementation: UpdateDescriptionMapper
    ): Mapper<UpdateDescriptionModel, UpdateDescriptionResponse>

    @Binds
    abstract fun provideProfileMapper(
        implementation: ProfileMapper
    ): Mapper<Profile, LoginUserResponse>

    @Binds
    abstract fun provideBeerDBMapper(
        implementation: BeerDBMapper
    ): Mapper<BeerListItem, Beer>

    @Binds
    abstract fun provideBeerMapper(
        implementation: BeerMapper
    ): Mapper<Beer, com.example.punk_api.api.model.BeerResponse>

    @Binds
    abstract fun provideAmountMapper(
        implementation: AmountMapper
    ): Mapper<Ingredients.Amount, com.example.punk_api.api.model.BeerResponse.IngredientsResponse.AmountResponse>

    @Binds
    abstract fun provideBoilVolumeMapper(
        implementation: BoilVolumeMapper
    ): Mapper<BoilVolume, com.example.punk_api.api.model.BeerResponse.BoilVolumeResponse>

    @Binds
    abstract fun provideFermentationMapper(
        implementation: FermentationMapper
    ): Mapper<Method.Fermentation, com.example.punk_api.api.model.BeerResponse.MethodResponse.FermentationResponse>

    @Binds
    abstract fun provideHopsMapper(
        implementation: HopsMapper
    ): Mapper<Ingredients.Hops, com.example.punk_api.api.model.BeerResponse.IngredientsResponse.HopsResponse>

    @Binds
    abstract fun provideIngredientsMapper(
        implementation: IngredientsMapper
    ): Mapper<Ingredients, com.example.punk_api.api.model.BeerResponse.IngredientsResponse>

    @Binds
    abstract fun provideMaltMapper(
        implementation: MaltMapper
    ): Mapper<Ingredients.Malt, com.example.punk_api.api.model.BeerResponse.IngredientsResponse.MaltResponse>

    @Binds
    abstract fun provideMashTempMapper(
        implementation: MashTempMapper
    ): Mapper<Method.MashTemp, com.example.punk_api.api.model.BeerResponse.MethodResponse.MashTempResponse>

    @Binds
    abstract fun provideMethodMapper(
        implementation: MethodMapper
    ): Mapper<Method, com.example.punk_api.api.model.BeerResponse.MethodResponse>

    @Binds
    abstract fun provideTempMapper(
        implementation: TempMapper
    ): Mapper<Method.Temp, com.example.punk_api.api.model.BeerResponse.MethodResponse.TempResponse>

    @Binds
    abstract fun provideVolumeMapper(
        implementation: VolumeMapper
    ): Mapper<Volume, com.example.punk_api.api.model.BeerResponse.VolumeResponse>
}