{-# LANGUAGE CPP #-}
{-# OPTIONS_GHC -fno-warn-missing-import-lists #-}
{-# OPTIONS_GHC -fno-warn-implicit-prelude #-}
module Paths_haskell_test (
    version,
    getBinDir, getLibDir, getDataDir, getLibexecDir,
    getDataFileName, getSysconfDir
  ) where

import qualified Control.Exception as Exception
import Data.Version (Version(..))
import System.Environment (getEnv)
import Prelude

#if defined(VERSION_base)

#if MIN_VERSION_base(4,0,0)
catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
#else
catchIO :: IO a -> (Exception.Exception -> IO a) -> IO a
#endif

#else
catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
#endif
catchIO = Exception.catch

version :: Version
version = Version [1,0] []
bindir, libdir, datadir, libexecdir, sysconfdir :: FilePath

bindir     = "C:\\Users\\Mazlum\\AppData\\Roaming\\cabal\\bin"
libdir     = "C:\\Users\\Mazlum\\AppData\\Roaming\\cabal\\x86_64-windows-ghc-8.0.1\\haskell-test-1.0-4jfLD4QJihM2UTDJE8Ycco"
datadir    = "C:\\Users\\Mazlum\\AppData\\Roaming\\cabal\\x86_64-windows-ghc-8.0.1\\haskell-test-1.0"
libexecdir = "C:\\Users\\Mazlum\\AppData\\Roaming\\cabal\\haskell-test-1.0-4jfLD4QJihM2UTDJE8Ycco"
sysconfdir = "C:\\Users\\Mazlum\\AppData\\Roaming\\cabal\\etc"

getBinDir, getLibDir, getDataDir, getLibexecDir, getSysconfDir :: IO FilePath
getBinDir = catchIO (getEnv "haskell_test_bindir") (\_ -> return bindir)
getLibDir = catchIO (getEnv "haskell_test_libdir") (\_ -> return libdir)
getDataDir = catchIO (getEnv "haskell_test_datadir") (\_ -> return datadir)
getLibexecDir = catchIO (getEnv "haskell_test_libexecdir") (\_ -> return libexecdir)
getSysconfDir = catchIO (getEnv "haskell_test_sysconfdir") (\_ -> return sysconfdir)

getDataFileName :: FilePath -> IO FilePath
getDataFileName name = do
  dir <- getDataDir
  return (dir ++ "\\" ++ name)
