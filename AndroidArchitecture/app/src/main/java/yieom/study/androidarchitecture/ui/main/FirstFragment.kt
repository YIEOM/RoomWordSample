package yieom.study.androidarchitecture.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import yieom.study.androidarchitecture.R
import yieom.study.androidarchitecture.data.api.interpark.InterparkAPI
import yieom.study.androidarchitecture.data.api.interpark.ResultGetBestSellerDto
import yieom.study.androidarchitecture.data.api.naver.NaverAPI
import yieom.study.androidarchitecture.data.api.naver.ResultGetSearchNews
import yieom.study.androidarchitecture.data.api.naver.ResultTransferPapago
import yieom.study.androidarchitecture.databinding.FragmentFirstBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    val TAG = javaClass.simpleName
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirstBinding.inflate(inflater,container,false)
        return binding.root
    }

    private val model: FirstViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        binding.btnSend.setOnClickListener {
            mainModel.sendMessage("From First")
        }

        binding.btnToSecond.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .add(R.id.container, SecondFragment())
                .commit()
        }

        binding.model = model

        model.getCount().observe(viewLifecycleOwner, Observer {
            binding.tvCount.text = "$it"
        })

        binding.btnCountUp.setOnClickListener {
            model.setCount(1)
        }

        binding.btnCallApiGetSearchNews.setOnClickListener {
            callApiGetSearchNes()
        }

        binding.btnCallApiTransferPapago.setOnClickListener {
            callApiTransferPapago()
        }

        binding.btnCallApiGetBestSeller.setOnClickListener {
            callApiBestSeller()
        }
    }

    val CLIENT_ID = "MVWzJo227bpyP3VMW06m"
    val CLIENT_SECRET = "WKF2sl4C6H"
    val BASE_URL_NAVER_API = "https://openapi.naver.com/"
    private fun callApiGetSearchNes() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_NAVER_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(NaverAPI::class.java)
        val callGetSearchNews = api.getSearchNews(CLIENT_ID,CLIENT_SECRET,"테스트")

        callGetSearchNews.enqueue(object: Callback<ResultGetSearchNews> {
            override fun onResponse(
                call: Call<ResultGetSearchNews>,
                response: Response<ResultGetSearchNews>
            ) {
                Log.d(TAG,"callApiGetSearchNes, 성공 : ${response.raw()}")
            }

            override fun onFailure(call: Call<ResultGetSearchNews>, t: Throwable) {
                Log.d(TAG,"callApiGetSearchNes, 실패 : $t")
            }
        })
    }

    private fun callApiTransferPapago() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_NAVER_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(NaverAPI::class.java)
        val callPostTransferPapago = api.transferPapago(CLIENT_ID,CLIENT_SECRET,
            "ko", "en", "테스트입니다. 이거 번역해주세요.")

        callPostTransferPapago.enqueue(object: Callback<ResultTransferPapago> {
            override fun onResponse(
                call: Call<ResultTransferPapago>,
                response: Response<ResultTransferPapago>
            ) {
                Log.d(TAG,"callApiTransferPapago, 성공 : ${response.raw()}")
            }

            override fun onFailure(call: Call<ResultTransferPapago>, t: Throwable) {
                Log.d(TAG,"callApiTransferPapago, 실패 : $t")
            }
        })
    }

    val BASE_URL_INTERPARK_API = "https://book.interpark.com/"
    private fun callApiBestSeller() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_INTERPARK_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(InterparkAPI::class.java)
        val callGetBestSeller = api.getBestSeller("38845BE9BD0EBEDF271A2D5BC770C5BEEBB2D38910F504545CE384C6692DA6D4")

        callGetBestSeller.enqueue(object: Callback<ResultGetBestSellerDto> {
            override fun onResponse(
                call: Call<ResultGetBestSellerDto>,
                response: Response<ResultGetBestSellerDto>
            ) {
                Log.d(TAG,"callApiBestSeller, 성공 : ${response.raw()}")
            }

            override fun onFailure(call: Call<ResultGetBestSellerDto>, t: Throwable) {
                Log.d(TAG,"callApiBestSeller, 실패 : $t")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}