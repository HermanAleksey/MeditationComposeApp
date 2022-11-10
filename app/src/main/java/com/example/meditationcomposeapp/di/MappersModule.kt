package com.example.meditationcomposeapp.di

import com.example.meditationcomposeapp.data_source.entity.BeerResponse
import com.example.meditationcomposeapp.data_source.entity.LoginUserResponse
import com.example.meditationcomposeapp.data_source.entity.UpdateDescriptionResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.data_source.mappers.profile.ProfileMapper
import com.example.meditationcomposeapp.data_source.mappers.profile.UpdateDescriptionMapper
import com.example.meditationcomposeapp.data_source.mappers.punk.*
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
    abstract fun provideBeerMapper(
        implementation: BeerMapper
    ): Mapper<Beer, BeerResponse>

    @Binds
    abstract fun provideAmountMapper(
        implementation: AmountMapper
    ): Mapper<Ingredients.Amount, BeerResponse.IngredientsResponse.AmountResponse>

    @Binds
    abstract fun provideBoilVolumeMapper(
        implementation: BoilVolumeMapper
    ): Mapper<BoilVolume, BeerResponse.BoilVolumeResponse>

    @Binds
    abstract fun provideFermentationMapper(
        implementation: FermentationMapper
    ): Mapper<Method.Fermentation, BeerResponse.MethodResponse.FermentationResponse>

    @Binds
    abstract fun provideHopsMapper(
        implementation: HopsMapper
    ): Mapper<Ingredients.Hops, BeerResponse.IngredientsResponse.HopsResponse>

    @Binds
    abstract fun provideIngredientsMapper(
        implementation: IngredientsMapper
    ): Mapper<Ingredients, BeerResponse.IngredientsResponse>

    @Binds
    abstract fun provideMaltMapper(
        implementation: MaltMapper
    ): Mapper<Ingredients.Malt, BeerResponse.IngredientsResponse.MaltResponse>

    @Binds
    abstract fun provideMashTempMapper(
        implementation: MashTempMapper
    ): Mapper<Method.MashTemp, BeerResponse.MethodResponse.MashTempResponse>

    @Binds
    abstract fun provideMethodMapper(
        implementation: MethodMapper
    ): Mapper<Method, BeerResponse.MethodResponse>

    @Binds
    abstract fun provideTempMapper(
        implementation: TempMapper
    ): Mapper<Method.Temp, BeerResponse.MethodResponse.TempResponse>

    @Binds
    abstract fun provideVolumeMapper(
        implementation: VolumeMapper
    ): Mapper<Volume, BeerResponse.VolumeResponse>
}