package com.example.punk_source.internal

import com.example.common.mapper.Mapper
import com.example.core.model.beer_sorts.*
import com.example.punk_source.api.mapper.*
import com.example.punk_source.api.model.BeerResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MappersModule {

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